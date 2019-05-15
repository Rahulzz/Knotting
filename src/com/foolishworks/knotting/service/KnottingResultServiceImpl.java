package com.foolishworks.knotting.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.ServiceDetails;
import com.foolishworks.knotting.entity.Services;
import com.foolishworks.knotting.intf.KnottingHomeService;
import com.foolishworks.knotting.intf.KnottingResultDao;
import com.foolishworks.knotting.intf.KnottingResultService;
import com.foolishworks.knotting.intf.KnottingServicesDao;
import com.foolishworks.knotting.intf.KnottingVendorServiceDao;
import com.foolishworks.knotting.utils.CloudinaryImageUtil;
import com.foolishworks.knotting.utils.GeneralUtils;
import com.foolishworks.knotting.utils.SendMessageUtil;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
@Transactional
public class KnottingResultServiceImpl extends KnottingConstants implements KnottingResultService {

	@Autowired
	KnottingResultDao knottingResultDao;

	@Autowired
	KnottingServicesDao knottingServicesDao;
	
	@Autowired
	KnottingVendorServiceDao knottingVendorServiceDao;

	@Autowired
	KnottingHomeService knottingHomeService;

	@Autowired
	GeneralUtils generalUtils;

	@Autowired
	CloudinaryImageUtil cloudinaryImageUtil;

	@Autowired
	SendMessageUtil sendMessageUtil;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingResultServiceImpl.class);

	public Long totalVendorCount(Long serviceId, String cityName, String range, String urgency, String filter){

		slf4jLogger.debug("Entering function {}", "totalVendorCount");

		Long vendorCount = null;

		Map<String, Integer> rangeOptions = rangeOptions(serviceId, range);
		
		StringBuilder queryBuilder = new StringBuilder();
		
		// service id is mandatory
		queryBuilder.append("Select count(vendors.memberServiceId) from MemberServices vendors where services.servicesId = "+serviceId);
		
		// add city if required
		if(cityName != null && !"".equals(cityName)){
			queryBuilder.append(" and memberEntries.additionalAreasServiced like '%"+cityName+"%'");
		}
		
		// add price range if required
		if(range != null && !"".equals(range)){
			if(rangeOptions.get(lowerRangeKey) > 0 || rangeOptions.get(higherRangeKey) > 0 ){
				queryBuilder.append(" and ");
				if(rangeOptions.get(higherRangeKey) == 0){
					queryBuilder.append("cast(priceRange as long) > cast('"+rangeOptions.get(lowerRangeKey).toString()+"' as long)");
				}
				else{
					queryBuilder.append("cast(priceRange as long) between cast('"+rangeOptions.get(lowerRangeKey).toString()+"' as long) and cast('"+rangeOptions.get(higherRangeKey).toString()+"' as long)");
				}
			}
		}
		
		// add urgency if required
		if(urgency != null && !"".equals(urgency) && emergencyName.equals(urgency)){
			queryBuilder.append(" and memberEntries.emergencyRequestAccepted = '"+emergencyCode+"'");
		}
		
		// add filter if required
		if(filter != null && !"".equals(filter)){
			String[] filterCriteria = filter.split(";");
			for(String criteria : filterCriteria){
				if(criteria.split(":").length == 3){
					String columnName = criteria.split(":")[0];
					String fieldType = criteria.split(":")[1];
					String[] values = criteria.split(":")[2].split("\\|");
					if(values.length > 0){
						if(filterFieldTypeRange.equals(fieldType)){
							if(values.length == 2){
								queryBuilder.append(" and cast("+columnName+" as long) between cast('"+values[0]+"' as long) and cast('"+values[1]+"' as long)");
							}
						}
						else{
							if(values.length == 1){
								queryBuilder.append(" and "+columnName+" = '"+values[0]+"'");
							}
							else {
								StringBuilder valueList = new StringBuilder();
								for(int i=0; i<values.length; i++){
									valueList.append("'"+values[i]+"'");
									if(i != (values.length-1)){
										valueList.append(",");
									}
								}
								
								queryBuilder.append(" and "+columnName+" IN ("+valueList.toString()+")");
							}
						}
					}
				}				
			}
		}
		
		vendorCount = knottingResultDao.totalVendorCount(queryBuilder.toString());

		slf4jLogger.debug("Exiting function {}", "totalVendorCount");

		return vendorCount;
	}

	public List<MemberServices> fetchResultData(Long serviceId, String cityName, String range, String urgency, String filter, String sort, int firstRecord, int lastRecord){

		slf4jLogger.debug("Entering function {}", "fetchResultData");

		List<MemberServices> memberServicesList = null;

		Map<String, Integer> rangeOptions = rangeOptions(serviceId, range);
		
		StringBuilder queryBuilder = new StringBuilder();
		
		// service id is mandatory (fetch only active records)
		queryBuilder.append("from MemberServices where services.servicesId = "+serviceId+" and serviceStatus = 'Y'");
		
		// add city if required
		if(cityName != null && !"".equals(cityName)){
			queryBuilder.append(" and memberEntries.additionalAreasServiced like '%"+cityName+"%'");
		}
		
		// add price range if required
		if(range != null && !"".equals(range)){
			if(rangeOptions.get(lowerRangeKey) > 0 || rangeOptions.get(higherRangeKey) > 0 ){
				queryBuilder.append(" and ");
				if(rangeOptions.get(higherRangeKey) == 0){
					queryBuilder.append("cast(priceRange as long) > cast('"+rangeOptions.get(lowerRangeKey).toString()+"' as long)");
				}
				else{
					queryBuilder.append("cast(priceRange as long) between cast('"+rangeOptions.get(lowerRangeKey).toString()+"' as long) and cast('"+rangeOptions.get(higherRangeKey).toString()+"' as long)");
				}
			}
		}
		
		// add urgency if required
		if(urgency != null && !"".equals(urgency) && emergencyName.equals(urgency)){
			queryBuilder.append(" and memberEntries.emergencyRequestAccepted = '"+emergencyCode+"'");
		}
		
		// add filter if required
		if(filter != null && !"".equals(filter)){
			String[] filterCriteria = filter.split(";");
			for(String criteria : filterCriteria){
				if(criteria.split(":").length == 3){
					String columnName = criteria.split(":")[0];
					String fieldType = criteria.split(":")[1];
					String[] values = criteria.split(":")[2].split("\\|");
					if(values.length > 0){
						if(filterFieldTypeRange.equals(fieldType)){
							if(values.length == 2){
								queryBuilder.append(" and cast("+columnName+" as long) between cast('"+values[0]+"' as long) and cast('"+values[1]+"' as long)");
							}
						}
						else{
							if(values.length == 1){
								queryBuilder.append(" and "+columnName+" = '"+values[0]+"'");
							}
							else {
								StringBuilder valueList = new StringBuilder();
								for(int i=0; i<values.length; i++){
									valueList.append("'"+values[i]+"'");
									if(i != (values.length-1)){
										valueList.append(",");
									}
								}
								
								queryBuilder.append(" and "+columnName+" IN ("+valueList.toString()+")");
							}
						}
					}
				}				
			}
		}
		
		// add sort if required
		if(sort != null && !"".equals(sort)){
			if(sortTypeCostAscending.equals(sort)){
				queryBuilder.append(" order by cast(priceRange as long) asc");
			}
			else if(sortTypeCostDescending.equals(sort)){
				queryBuilder.append(" order by cast(priceRange as long) desc");
			}
			else if(sortTypeExperienceAscending.equals(sort)){
				queryBuilder.append(" order by cast(experience as long) asc");
			}
			else if(sortTypeExperienceDescending.equals(sort)){
				queryBuilder.append(" order by cast(experience as long) desc");
			}
		}
		
		memberServicesList = knottingResultDao.fetchResultData(queryBuilder.toString(), firstRecord, lastRecord);

		/* change image url to transformed image url */
		for(MemberServices memberServices : memberServicesList){
			if(memberServices.getImagePath1() != null && memberServices.getImagePath1().length() > 0){
				memberServices.setImagePath1Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath1()));
			}
			if(memberServices.getImagePath2() != null && memberServices.getImagePath2().length() > 0){
				memberServices.setImagePath2Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath2()));
			}
			if(memberServices.getImagePath3() != null && memberServices.getImagePath3().length() > 0){
				memberServices.setImagePath3Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath3()));
			}
			if(memberServices.getImagePath4() != null && memberServices.getImagePath4().length() > 0){
				memberServices.setImagePath4Thumbnail(cloudinaryImageUtil.thumbnailUrl(memberServices.getImagePath4()));
			}
		}

		slf4jLogger.debug("Exiting function {}", "fetchResultData");

		return memberServicesList;
	}

	public Services fetchServiceRecord(Long serviceId){

		slf4jLogger.debug("Entering function {}", "fetchServiceRecord");

		Services services = knottingServicesDao.fetchServices(serviceId);

		slf4jLogger.debug("Exiting function {}", "fetchServiceRecord");

		return services;
	}

	public void processVendorInformRequest(String phoneNumber, Long serviceId, String cityName, String range, String urgency) throws UnirestException{

		slf4jLogger.debug("Entering function {}", "processVendorInformRequest");

		StringBuilder phoneList = new StringBuilder();
		int vendorCount = 0;

		List<MemberServices> memberServicesList = fetchResultData(serviceId, cityName, range, urgency, null, null, 0, maxResultInformVendor);

		Integer[] randomNumber = generalUtils.generateRandomNumber(maxResultInformVendor);

		if(memberServicesList != null){
			if(memberServicesList.size() > 30){
				for(Integer index : randomNumber){
					if(vendorCount < 20 && index <= (memberServicesList.size() - 1)){

						phoneList.append(memberServicesList.get(index).getMemberEntries().getPhone1()).append(",");

						if(vendorCount < 20){
							vendorCount++;
						}
					}
				}
			}
			else{
				for(MemberServices memberServices : memberServicesList){
					phoneList.append(memberServices.getMemberEntries().getPhone1()).append(",");

					if(vendorCount < 19){
						vendorCount++;
					}
					else{
						break;
					}
				}
			}

			sendMessageUtil.sendVendorInformSmsToUser(phoneList.toString(), memberServicesList.get(0).getServices().getName(), phoneNumber);

		}

		slf4jLogger.debug("Exiting function {}", "processVendorInformRequest");
	}

	private Map<String, Integer> rangeOptions(Long servicesId, String range){

		slf4jLogger.debug("Entering function {}", "rangeOptions");

		Services services = knottingServicesDao.fetchServices(servicesId);

		Map<String, Integer> rangeOptions = new HashMap<String, Integer>();

		rangeOptions.put(lowerRangeKey, new Integer(0));
		rangeOptions.put(higherRangeKey, new Integer(0));

		if(priceRangeCheap.toLowerCase().equals(range)){
			rangeOptions.put(higherRangeKey, Integer.parseInt(services.getLowerPrice()));
		}
		else if(priceRangeModerate.toLowerCase().equals(range)){
			rangeOptions.put(lowerRangeKey, Integer.parseInt(services.getLowerPrice()));
			rangeOptions.put(higherRangeKey, Integer.parseInt(services.getHigherPrice()));
		}
		else if(priceRangeCostly.toLowerCase().equals(range)){
			rangeOptions.put(lowerRangeKey, Integer.parseInt(services.getHigherPrice()));
		}

		slf4jLogger.debug("Exiting function {}", "rangeOptions");

		return rangeOptions;

	}
	
	public String priceRangeTitle(Long servicesId, String range){

		slf4jLogger.debug("Entering function {}", "priceRangeTitle");
		
		String displayRange = null;
		
		Services services = knottingServicesDao.fetchServices(servicesId);
		
		if(range.equals(priceRangeAll.toLowerCase())){
			displayRange = "All";
		}
		else if(range.equals(priceRangeCheap.toLowerCase())){
			displayRange = priceRangeCheap+" (lesser than "+services.getLowerPrice()+" INR)";
		}
		else if(range.equals(priceRangeModerate.toLowerCase())){
			displayRange = priceRangeModerate+" ("+services.getLowerPrice()+" INR to "+services.getHigherPrice()+" INR)";
		}
		else if(range.equals(priceRangeCostly.toLowerCase())){
			displayRange = priceRangeCostly+" (more than "+services.getHigherPrice()+" INR)";
		}

		slf4jLogger.debug("Exiting function {}", "priceRangeTitle");
		
		return displayRange;
		
	}
	
	public List<ServiceDetails> fetchServiceDetails(Long servicesId, List<MemberServices> memberServicesList) throws Exception{

		slf4jLogger.debug("Entering function {}", "fetchServiceDetails");
		
		List<ServiceDetails> serviceDetails = knottingVendorServiceDao.fetchServiceDetails(servicesId);
				
		//Calculate the lower and upper range
		for(ServiceDetails serviceDetail : serviceDetails){
			if(serviceDetail.getServiceDetailCode().endsWith("PRC")){
				String fieldName = serviceDetail.getServiceDetailFieldName();
				String methodName = "get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				
				Long lowerRange = null;
				Long upperRange = null;
				
				for(MemberServices memberService : memberServicesList){				
					Method method = memberService.getClass().getMethod(methodName);
					String value = (String)method.invoke(memberService);
					List<Long> priceRanges = new ArrayList<Long>();
					
					if(value != null){
						if(value.contains(",")){
							for(String price : value.split(",")){
								priceRanges.add(new Long(price)); 
							}						
						}
						else{						
							priceRanges.add(new Long(value));
						}
						
						for(Long longValue : priceRanges){
							if(lowerRange == null && upperRange == null){
								lowerRange = new Long(longValue);
								upperRange = new Long(longValue);
							}
							else if(lowerRange !=null && upperRange != null){
								if(longValue.intValue() < lowerRange.intValue()){
									lowerRange = longValue;
								}
								else if(longValue.intValue() > upperRange.intValue()){
									upperRange = longValue;
								}
							}
						}
					}
				}
				
				serviceDetail.setServiceDetailAnswerList(lowerRange+"#"+upperRange);
			}
		}

		slf4jLogger.debug("Exiting function {}", "fetchServiceDetails");
		
		return serviceDetails;
		
	}

	public Long fetchServiceIdByCode(String serviceCode){

		slf4jLogger.debug("Entering function {}", "fetchServiceIdByCode");

		Long serviceId = knottingResultDao.fetchServiceIdByCode(serviceCode);

		slf4jLogger.debug("Exiting function {}", "fetchServiceIdByCode");

		return serviceId;
	}

}
