package restClient;
import java.util.ArrayList;
import java.util.Date;

import restClient.json.*;

import javax.ws.rs.core.Form;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import bpelSpawner.soapClient;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;


//import bpelSpawner.soapClient;



public class restJavaOperation {

    final String REST_URI = "http://localhost:8080/DrivenRest/driven/";

    public restJavaOperation() {
    }

    //checkEmailCode
    public Integer getCheckEmailCode(String query_code){
    	
        WebClient drivenClient = WebClient.create(REST_URI);
        drivenClient.path("/checkEmailCode/").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("Content-Type", "application/json");

        drivenClient.query("code", query_code);//("rid=1");
        

        Response s = drivenClient.get();
    	
    	if(s.getStatus()==404){
    		return null;
    	}
    	
        String result = s.readEntity(String.class);

    	JSONObject jObj = new JSONObject(result);

    	Integer ret = jObj.getInt("id");

    	
    	return ret;
    	
    }

    public Registration getRegistrationDriver(Integer rid) {
        WebClient drivenClient = WebClient.create(REST_URI);
        drivenClient.path("/registrations").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "driver");
        drivenClient.query("rid", rid.toString());//("rid=1");

        drivenClient.header("Content-Type", "application/json");
        Response s = drivenClient.get();

        if (s.getStatus() == 200) {
            String result = s.readEntity(String.class);
            JSONObject jObj = new JSONObject(result);

            JSONObject jDriverObj = (JSONObject) jObj.get("driver");
            String address = jDriverObj.getString("address");
            String lastName = jDriverObj.getString("lastName");
            String firstName = jDriverObj.getString("firstName");
            String licenseNumber = jDriverObj.getString("licenseNumber");
            String email = jDriverObj.getString("email");
            Driver currDriver = new Driver(lastName, firstName, licenseNumber, address, email);

//            System.out.println("vallidTill==>" +  jObj.getLong("validTill"));
//            System.out.println("registrationNumber==>" +jObj.getString("registrationNumber"));
//            System.out.println("rID==>" +jObj.getInt("rID"));
            Date date = new Date();
            date.setTime(jObj.getLong("validTill"));

            Registration renewalNotice = new Registration(jObj.getInt("rID"), jObj.getString("registrationNumber"),
                    date, currDriver);
            return renewalNotice;

        } else {
            return null;
        }

    }

    //get registrations, id is inptutted as null if officer
    public ArrayList<Registration> getRegistrationsOfficer(){
        WebClient drivenClient = WebClient.create(REST_URI);
        drivenClient.path("/registrations").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "RMSofficer");
        drivenClient.header("Content-Type", "application/json");
        Response s = drivenClient.get();
        String result = s.readEntity(String.class);
        
        ArrayList<Registration> gAR =  new ArrayList<Registration>();
        JSONArray jArrObj = new JSONArray(result);        
        for(int i=0;i<jArrObj.length(); i++){
        	JSONObject jsonTree = jArrObj.getJSONObject(i);
	          
        	JSONObject jDriverObj = (JSONObject) jsonTree.get("driver");
            String address = jDriverObj.getString("address");
            String lastName = jDriverObj.getString("lastName");
            String firstName = jDriverObj.getString("firstName");
            String licenseNumber = jDriverObj.getString("licenseNumber");
            String email = jDriverObj.getString("email");
            Driver currDriver = new Driver(lastName, firstName, licenseNumber, address, email);
//            	
//            System.out.println("vallidTill==>" +  jsonTree.getLong("validTill"));
//            System.out.println("registrationNumber==>" +jsonTree.getString("registrationNumber"));
//            System.out.println("rID==>" +jsonTree.getInt("rID"));
            Date date = new Date();
            date.setTime(jsonTree.getLong("validTill"));

            Registration renewalNotice = new Registration(jsonTree.getInt("rID"), jsonTree.getString("registrationNumber"),
                    date, currDriver);
            gAR.add(renewalNotice);
            
	    }

        
		return gAR;
        
 
    }
    
    public Payment getPaymentsDriver(Integer id){
    	WebClient drivenClient = WebClient.create(REST_URI);
    	drivenClient.path("/payments").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "driver");
        drivenClient.query("pid", id.toString());
        
        drivenClient.header("Content-Type", "application/json");
        Response s = drivenClient.get();
        String result = s.readEntity(String.class);
        
        JSONObject jObj = new JSONObject(result);
        
        Date date = new Date();
        date.setTime(jObj.getLong("paid_date"));
        
        Payment retPayment = new Payment(jObj.getInt("pid"), jObj.getInt("nid"),jObj.getInt("amount"),
        		jObj.getInt("credit_card_number"),jObj.getString("credit_card_name"),jObj.getInt("credit_card_ccv"),date);
        
        
//        System.out.println("pid==>" +  jObj.getInt("pid"));
//        System.out.println("credit_card_number==>" +jObj.getInt("credit_card_number"));
//        System.out.println("credit_card_name==>" +jObj.getString("credit_card_name"));
//        System.out.println("credit_card_ccv==>" +jObj.getInt("credit_card_ccv"));
//        System.out.println("paid_date==>" +jObj.getLong("paid_date"));
//        System.out.println("nid==>" +jObj.getInt("nid"));
//        System.out.println("amount==>" +jObj.getInt("amount"));
    	
		return retPayment;
    	
    }
    
    public ArrayList<Payment> getPaymentsOfficer(){
    	WebClient drivenClient = WebClient.create(REST_URI);
    	drivenClient.path("/payments").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "RMSofficer");

        drivenClient.header("Content-Type", "application/json");
        Response s = drivenClient.get();
        String result = s.readEntity(String.class);
        ArrayList<Payment> aP = new  ArrayList<Payment>();
	    JSONArray jArrObj = new JSONArray(result);        
	    for(int i=0;i<jArrObj.length(); i++){
	    	
	    	JSONObject jObj = jArrObj.getJSONObject(i);
	    	System.out.println("pid==>" +  jObj.getInt("pid"));
	        System.out.println("credit_card_number==>" +jObj.getInt("credit_card_number"));
	        System.out.println("credit_card_name==>" +jObj.getString("credit_card_name"));
	        System.out.println("credit_card_ccv==>" +jObj.getInt("credit_card_ccv"));
	        System.out.println("paid_date==>" +jObj.getLong("paid_date"));
	        System.out.println("nid==>" +jObj.getInt("nid"));
	        System.out.println("amount==>" +jObj.getInt("amount"));
	        Date date = new Date();
	        date.setTime(jObj.getLong("paid_date"));
	        
	    	Payment p = new Payment(jObj.getInt("pid"), jObj.getInt("nid"), 
	    			jObj.getInt("amount"), jObj.getInt("credit_card_number"),
	    			jObj.getString("credit_card_name"),jObj.getInt("credit_card_ccv"),date);
	    	aP.add(p);
	    	
	        }
		return aP;
          	
    	
    }

    public RenewalNotice getRenewalNoticeDriver(Integer nid) {
        WebClient drivenClient = WebClient.create(REST_URI);
        drivenClient.path("/notices").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "driver");
        drivenClient.query("nid", nid.toString());

        drivenClient.header("Content-Type", "application/json");
        Response s = drivenClient.get();
        if (s.getStatus() == 200) {
            String result = s.readEntity(String.class);
            JSONObject jObj = new JSONObject(result);

            System.out.println("nid==>" +  jObj.getInt("nid"));
            System.out.println("status==>" +jObj.getString("status"));
            System.out.println("rid==>" +jObj.getInt("rid"));
            RenewalNotice renewalNotice = new RenewalNotice(jObj.getInt("nid"), jObj.getInt("rid"), jObj.getString("status"));
            return renewalNotice;
        } else {
            return null;
        }

    }
    
    public ArrayList<RenewalNotice> getRenewalNoticeOfficer(){
    	
    	WebClient drivenClient = WebClient.create(REST_URI);
    	drivenClient.path("/notices").accept(MediaType.APPLICATION_JSON);
    	drivenClient.header("authorization", "RMSofficer");
	      
	    drivenClient.header("Content-Type", "application/json");
	    Response s = drivenClient.get();
	    String result = s.readEntity(String.class);
	    JSONArray jArrObj = new JSONArray(result);
	    ArrayList<RenewalNotice> rN = new ArrayList<RenewalNotice>();
		for(int i=0;i<jArrObj.length(); i++){
            JSONObject jObj = jArrObj.getJSONObject(i);
	
            System.out.println("nid==>" +  jObj.getInt("nid"));
            System.out.println("status==>" +jObj.getString("status"));            
            System.out.println("rid==>" +jObj.getInt("rid"));
			RenewalNotice r = new RenewalNotice(jObj.getInt("nid"),jObj.getInt("rid"),jObj.getString("status"));
			rN.add(r);
           
            
        }
		
		return rN;
	}    	
    	
    public PaymentResponse postPayments(String nid, String fee){
    	WebClient drivenClient = WebClient.create(REST_URI);
    	
    	drivenClient.path("/payments").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "RMSofficer");
        drivenClient.header("Content-Type", "application/x-www-form-urlencoded"); 
    	
        Form form = new Form();
		form.param("nid", nid);

		form.param("fee", fee);
		
        Response s = drivenClient.post(form);
        System.out.print(s.getStatus());
        if(s.getStatus()==200){
	    	JSONObject jObj = new JSONObject(s.readEntity(String.class));	        
	    	JSONObject payment = (JSONObject) jObj.get("payment");
//	    	
//	        System.out.println(payment.getString("credit_card_name"));
//	    	System.out.println("pid==>" +  payment.getInt("pid"));
//	        System.out.println("credit_card_number==>" +payment.getInt("credit_card_number"));
//	        System.out.println("credit_card_name==>" +payment.getString("credit_card_name"));
//	        System.out.println("credit_card_ccv==>" +payment.getInt("credit_card_ccv"));
//	        System.out.println("paid_date==>" +payment.getLong("paid_date"));
//	        System.out.println("nid==>" +payment.getInt("nid"));
//	        System.out.println("amount==>" +payment.getInt("amount"));
//	        System.out.println("amount==>" +jObj.getString("link"));
	
	        Date date = new Date();
	        date.setTime(payment.getLong("paid_date"));
	        
	        Payment p = new Payment(payment.getInt("pid"), payment.getInt("nid"), 
	        		payment.getInt("amount"), payment.getInt("credit_card_number"),
	        		payment.getString("credit_card_name"),payment.getInt("credit_card_ccv"),date);
	        PaymentResponse pR = new PaymentResponse(p,jObj.getString("link") );
	        return pR;
	        
        }else{
        	return null;
        }
    }
    
    public ArrayList<RenewalNoticeResponse> postNotices(){
    	WebClient drivenClient = WebClient.create(REST_URI);
    	
    	drivenClient.path("/notices/newNotices").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("Authorization", "RMSofficer");
        drivenClient.header("Content-Type", "application/x-www-form-urlencoded"); 
        Form form = new Form();
        
        ArrayList<RenewalNoticeResponse> rN = new ArrayList<RenewalNoticeResponse>();

        Response s = drivenClient.post(form);
        if(s.getStatus()==200){
    	//JSONObject jObj = new JSONObject();
        JSONArray jArrObj = new JSONArray(s.readEntity(String.class)); 
        
        for(int i=0;i<jArrObj.length(); i++){
        	JSONObject jObj = jArrObj.getJSONObject(i);
            JSONObject jObjE = (JSONObject) jObj.get("renewalNotice");
            RenewalNotice rNi = new RenewalNotice(jObjE.getInt("nid"), jObjE.getInt("rid"), jObjE.getString("status"));
            RenewalNoticeResponse rNRi = new RenewalNoticeResponse(rNi, jObj.getString("link"));
   
            rN.add(rNRi);
        }
        	return rN;
        }else{
        	return null;
        }

    
    }
    
    public Integer deletePayments(String pid){
    	WebClient drivenClient = WebClient.create(REST_URI);
    	
    	drivenClient.path("/payments").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "RMSofficer");
        drivenClient.header("Content-Type", "application/x-www-form-urlencoded"); 
		drivenClient.query("pid", pid);

        Response s = drivenClient.delete();
        
        System.out.println(s.getStatus());
        return s.getStatus();
    
    }
    
    public Integer deleteNotice(String pid){
    	WebClient drivenClient = WebClient.create(REST_URI);
    	
    	drivenClient.path("/notices").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "driver");
        drivenClient.header("Content-Type", "application/x-www-form-urlencoded"); 
		drivenClient.query("nid", pid);

        Response s = drivenClient.delete();
        
        JSONObject jArrObj = new JSONObject(s.readEntity(String.class));        
        
        
        System.out.println(jArrObj.getString("status"));
        System.out.println(s.getStatus());
        return s.getStatus();
    
    }
    
    public Integer putPayments(String pid, String cc_number, String cc_name, String cc_ccv){
    	WebClient drivenClient = WebClient.create(REST_URI);
    	
    	drivenClient.path("/payments").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "driver");
        drivenClient.header("Content-Type", "application/x-www-form-urlencoded"); 


        Form form = new Form();
		form.param("pid", pid);
		form.param("cc_number", cc_number);
		form.param("cc_name", cc_name);
		form.param("cc_ccv", cc_ccv);
		
		
        Response s = drivenClient.put(form);
        
               
        JSONObject jArrObj = new JSONObject(s.readEntity(String.class));        
        
        
        System.out.println(s.getStatus());
        return s.getStatus();

    }
    
    public Integer putRegistration(String rid, String email, String address){
    	WebClient drivenClient = WebClient.create(REST_URI);
    	
    	drivenClient.path("/registrations").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "RMSofficer");
        drivenClient.header("Content-Type", "application/x-www-form-urlencoded"); 


        Form form = new Form();
		form.param("rid", rid);
		form.param("email", email);
		form.param("address", address);
		
		
        Response s = drivenClient.put(form);
        
               
        JSONObject jArrObj = new JSONObject(s.readEntity(String.class));        
        
        
        System.out.println(s.getStatus());
        return s.getStatus();

    }
    
    public Integer putNoticesDriver(String nid, String status){
    	WebClient drivenClient = WebClient.create(REST_URI);
    	
    	drivenClient.path("/notices").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "driver");
        drivenClient.header("Content-Type", "application/x-www-form-urlencoded"); 


        Form form = new Form();
		form.param("nid", nid);
		form.param("status", status);
		
        Response s = drivenClient.put(form);
        
               
        JSONObject jArrObj = new JSONObject(s.readEntity(String.class));        
        
        
        System.out.println(s.getStatus());
        return s.getStatus();

    }

    public Integer putNoticesOfficer(String nid, String status){
    	WebClient drivenClient = WebClient.create(REST_URI);
    	
    	drivenClient.path("/notices").accept(MediaType.APPLICATION_JSON);
        drivenClient.header("authorization", "RMSofficer");
        drivenClient.header("Content-Type", "application/x-www-form-urlencoded"); 


        Form form = new Form();
		form.param("nid", nid);
		form.param("status", status);
		
        Response s = drivenClient.put(form);
        
               
        JSONObject jArrObj = new JSONObject(s.readEntity(String.class));        
        
        
        System.out.println(s.getStatus());
        return s.getStatus();
    }
    
}
