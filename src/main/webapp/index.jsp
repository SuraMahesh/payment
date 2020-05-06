<%@ page import="java.util.List"%>
<%@ page import="com.paf.payment.paymentDataModel" %>

<!DOCTYPE html>
<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="view/bootstrap.min.css">
<script src="component/jquery-3.5.0.min.js"></script>
<script src="component/Patient.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm">
				<h3>
					Patient Payment details Adding Form 
				</h3>

				<form id="pat_Form"  name="pat_Form" method="post" action="index.jsp">

					<div class="form-group col-md-6">
						<div class="form-group ">
							<label for="IdentyCardNumber">Patient ID</label> <input type="text"
								class="form-control" id="nic" name="nic"
								placeholder="980451465V" required>
						</div>

						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="firstname">Card Number</label> <input type="text"
									class="form-control" id="f_name" name="f_name" placeholder="">
							</div>
							<div class="form-group col-md-6">
								<label for="lastname">Name of the card</label> <input type="text"
									class="form-control" id="l_name" name="l_name" placeholder="">
							</div>
						</div>

						<div class="form-group">
							<label for="patientEmail">Valid date</label> <input type="email"
								class="form-control" id="pat_mail" name="pat_mail"
								placeholder="">
						</div>
						<div class="form-group">
							<label for="contactNumber">Cvc code</label> <input
								type="text" class="form-control" id="mob_num" name="mob_num"
								placeholder="">
						</div>
						<div class="form-group">
							<label for="patbirthday">Amount</label> <input type="date"
								class="form-control" id="p_bday" name="p_bday" placeholder="">

						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="patpassword">Password</label> <input type="password"
									class="form-control" id="pass" name="pass" placeholder="">
							</div>
						</div>
						<br>
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
						<input id="hidPatSave" name="hidPatSave" type="hidden" value="">

					</div>
				</form>

			</div>
		</div>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>

		<div class="row">
			<div class="col-sm">
				<div id="divItemsGrid">
					<% 
 
 						paymentDataModel itemObj = new paymentDataModel();
 						out.print(itemObj.getCustomers());
										
 					%>
				</div>
			</div>
		</div>
	</div>



</body>
</html>