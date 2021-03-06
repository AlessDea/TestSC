<%@page import="logic.controllers.ProfileController"%>
<%@page import="logic.bean.PrivateTravelBean"%>
<%@page import="logic.controllers.ManagePrivateTravelController"%>
<%@page import="logic.bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Profile</title>
        <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
       
       <style>
           
           .img-logo {
               display: block;
               margin-left: 30px;
               margin-right: auto;
           }
       
           .affix {
               top: 0;
               width: 100%;
               z-index: 9999 !important;
           }
       
       
           .affix + .container-fluid {
               padding-top: 70px;
           }
       
       		.center {
			  text-align: center;
			}
       
           li a:hover:not(.active) {
               background-color: #f0ad4e;
               color: white;
           }
       </style>
       
       
    </head>
    <body>
		<%
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0
			response.setHeader("Expires", "0"); // Proxies
	
			if(session.getAttribute("user") == null) {
				response.sendRedirect("login.jsp");
			}
			else {

				ProfileController profileController = new ProfileController();
				UserBean user = (UserBean) session.getAttribute("user");
				String username = user.getUsername();
	
				List<PrivateTravelBean> privateTravelBeansLst = new ArrayList<>();
				privateTravelBeansLst = profileController.loadMyOldT(username);
		%>

        <div>
            <div class="container-fluid" style="background-color:#222">
                <img class="img-logo" src="Images/logoET.png">
                    </div>
            
        </div>
        
        <nav class="navbar navbar-inverse" data-spy="affix" data-offset-top="197">
            <div class="container-fluid">
                 <div class="collapse navbar-collapse" id="myNavbar">
                     <ul class="nav navbar-nav">
	                    <li><a href="home.jsp">Home</a></li>
                        <li><a href="searchFollower.jsp">Search follower</a></li>
                        <li class="active"><a href="profile.jsp">Profile</a></li>
                        <li><a href="notifs.jsp">Notifications</a></li>
                        <li><a href="settings.jsp">Settings</a></li>
	                </ul>
	                <ul class="nav navbar-nav navbar-right">
	                    <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
	                </ul>
                 </div>
             </div>
        </nav>
        
        <div class="container-fluid">
            <div class="row content">
                <div class="col-sm-3 sidenav">
                    <div id="usrInf" class="well center" style="background-color:#f8f8f8">
           	               <%
               				String pic = "";
               				pic = profileController.retrieveUserPhoto(username);
               				if(pic.equals(""))
               					out.println("<img src=\"Images/userButton.png\" class=\"img-circle\" height=\"55\" width=\"55\" alt=\"Avatar\">");
               				else {
               					String path = "";
               					pic = pic.replace("\\", "/");
               					String[] params;
               					params = pic.split("(?<=/)");
               					for(int i=0; i<params.length; i++) {
               						if(params[i].equalsIgnoreCase("Images/")) {     
               							for(int j=i; j<params.length; j++)
               								path = path.concat(params[j]);
               							break;
               						}
               					}
               					out.println("<img src=\"" + path + "\" class=\"img-circle\" height=\"140\" width=\"140\" alt=\"Avatar\">");
               				}
               				out.println("<br>");
               				out.println("<h4>" + user.getUsername() + " </h4>");
               				out.println("<h4>" + user.getEmail() + " </h4>");
               				String password = user.getPassword();
               				password = password.replaceAll(".", "&#9679;");
               				out.println("<h4>" + password + " </h4>");
               			%>
                    </div>
                   <ul class="nav nav-pills nav-stacked li2">
                        <li><a class="dropdown-toggle" data-toggle="dropdown" href="#">
	    					<span class="caret"></span> Upcoming Travels</a>
						    <ul class="dropdown-menu">
						      <li><a href="profile.jsp">Private</a></li>
						      <li><a href="profileUpPub.jsp">Public</a></li>
						    </ul>
                        </li>
						<li class="active dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
	    					<span class="caret"></span> Past Travels</a>
						<ul class="dropdown-menu">
						      <li class="active"><a href="profilePastPriv.jsp">Private</a></li>
						      <li><a href="profilePastPub.jsp">Public</a></li>
						</ul>
						</li>
                        <li><a href="followers.jsp">Following</a></li>
                        <li><a href="modifyUserInfo.jsp">Modify personal information</a></li>
                    </ul><br>
                    
                </div>
                
                <div id="trvls" class="col-sm-9">
                    <h4><small>MY PAST PRIVATE TRAVELS</small></h4>
                    <hr>
                    <%	
                    	if(privateTravelBeansLst.isEmpty())
            				out.println("<h3>No travels found</h3>");
                    	else {
                    		List<String> filenames = new ArrayList<>();
    	                    for(PrivateTravelBean privateTravelBean : privateTravelBeansLst) {
    	                    	filenames = profileController.retrieveTravelPhotos(privateTravelBean.getIdTravel());
    	                    	String pathTrav = "";
    	                    	String picTrav = "";
    	                    	List<String> paths = new ArrayList<>();
    	                    	
    	                    	for(String filename : filenames) {
    	                    		picTrav = filename;
    	                    		picTrav = picTrav.replace("\\", "/");
    	           					String[] params;
    	           					params = picTrav.split("(?<=/)");
    	           					pathTrav = "";
    	           					for(int i=0; i<params.length; i++) {
    	           						if(params[i].equalsIgnoreCase("Images/")) {     
    	           							for(int j=i; j<params.length; j++) 
    	           								pathTrav = pathTrav.concat(params[j]);
    	           							break;
    	           						}
    	           					}
    	           					paths.add(pathTrav);
    	                    	}
    	                    	
    	                    	String price = privateTravelBean.getHotelInfo().getPrice();
    	                    	if(price.contains("Less than")) 
    	                    		price = "Less than &euro; 50";
    	                    	else if(price.contains("50") && price.contains("100"))
    	                    		price = "&euro; 50 - &euro; 100";
                   				else if(price.contains("100") && price.contains("150"))
       	                    		price = "&euro; 100 - &euro; 150";
                   				else if(price.contains("150") && price.contains("225"))
       	                    		price = "&euro; 150 - &euro; 225";
    	                    	else if(price.contains("More than"))
    	                    		price = "More than &euro; 225"; 
    	                    		
    	                    	out.println("<br><div class=\"well\" style=\"background-color:#FFEBCD\"><p><b><h3>Travel's name: </b>" + privateTravelBean.getTravelName() + "</h3><b><h4>Destination: </b>" + privateTravelBean.getDestination() + "</h4></p><p><b>From: </b>" + privateTravelBean.getStartDate() + "</p><p><b>To: </b>" + privateTravelBean.getEndDate() + "</p><button type=\"button\" class=\"btn btn-warning btn-sm\" style=\"float:left\" data-toggle=\"collapse\" data-target=\"#info"+ privateTravelBean.getIdTravel() +"\"><span class=\"glyphicon glyphicon-info-sign\"></span> Info</button></div>");
    							out.println("<div id=\"info"+ privateTravelBean.getIdTravel() +"\" class=\"collapse well\" style=\"background-color:#ffffff\"><p><b>Descrizione:</b> "+ privateTravelBean.getDescription() +" </p>"
    				    					+"<p><b>Hotel:</b><a href=\" "+ privateTravelBean.getHotelInfo().getHotelLink() +" \" target=\"_blank\"> " + privateTravelBean.getHotelInfo().getHotelName() + "</a></p><p><b>Stars:</b> " + privateTravelBean.getHotelInfo().getStars() + "</p><p><b>Price per night:</b> " + price + "</p>" + "</p><p><b>Breakfast:</b> " + privateTravelBean.getHotelInfo().getBreakfast() + "</p>"
    				                       	+ "<p><b>Number of travelers:</b> " + privateTravelBean.getNumMaxUt() + "<p><b>Rooms:</b> " + privateTravelBean.getHotelInfo().getNumRooms() + "</p>");
                        		
    							for(String filePath : paths)
    								out.println("<img src=\"" + filePath + "\" style=\"width:300px; height:200px; margin:2px;\" alt=\"Photo\">");
    							
    							out.println("<ul style=\"list-style-type:disc;\"></ul></div>");
    							
    							out.println("<br><hr>");
    	                	}
                    	}
                    %>                    
                </div>
            </div>
        </div>
        <%
			}
        %>               
    </body>
</html>