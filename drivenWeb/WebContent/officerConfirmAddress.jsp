</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="navBar.html" %>
		  
<div class="row">
            <div class="col s12"><p></p></div>
            <div class="col s12 m4 l2"><p></p></div>
            <div class="col s12 m4 l8"><p></p>
            
            

      
	 <c:choose>
	  <c:when test="${exact}">
	     <div class="col s12 m6">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
              <span class="card-title">		ExactMatch found</span>
                 <p>    <c:out value="${message}"/></p>
            </div>
           
          </div>
        </div>
	<form class="col s12" action="officerhome" method="post">
		     
		        	<input type="hidden" name="action" value="createPayment" />
		            <br>
		             <p class="flow-text">Create Payment</p>
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input value="" id="amount" name="amount" type="number" class="validate">
                                                    <label class="active" for="amount" required>Amount for
                                                        Payment</label>
                                                </div>
                                            </div>
                                            
                                                                                        
		            <button class="btn waves-effect waves-light" type="submit" name="action">Confirm
			            <i class="material-icons right">input</i>
		            </button>
			        </form>
			          </c:when>
	  <c:otherwise>
	   <div class="col s12 m6">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
              <span class="card-title">		ExactMatch Not found</span>
                 <p>    <c:out value="${message}"/></p>
            </div>
           
          </div>
          </div>	
					<form class="col s12" action="officerhome" method="post">
       
        	        	<input type="hidden" name="action" value="rejectPayment" />
		        	
		            <br>
		             <p class="flow-text">Reject Payment</p>
                                        <div class="row">
                                            <div class="input-field col s6">
                                                <input value="" id="rejection" name="rejection" type="text" class="validate">
                                                    <label class="active" for="address" required>Reason for Rejections</label>
                                                </div>
                                            </div>
                                                                 
                                                                                        
		            <button class="btn waves-effect waves-light" type="submit" name="action">Confirm
			            <i class="material-icons right">input</i>
		            </button>
			        </form>
		         </c:otherwise>
	</c:choose>
</div>
                                        <div class="col s12 m4 l2"><p></p></div>
</body>
</html>
