$(document).ready(function()
		{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();

});

$(document).on("click", "#btnSave", function(event)
		{ 
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");


	var status = validatePatientForm();
	if(status!= true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}


	var type  = ($("#hidPatSave").val() == "") ? "POST" : "PUT";
	console.log(type)
	$.ajax(
			{
				url: "paymentAPI",
				type: type,
				data : $("#pat_Form").serialize(),
				dataType :"text",
				complete : function(response, status)
				{
					onPatientSaveComplete(response.responseText, status);
					console.log(response.responseText)
					console.log("***************************************************************")
					console.log(status)
				}
			});
		});


$(document).on("click", ".btnUpdate", function (event) {
	$("#hidPatSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	$("#patientId").val($(this).closest("tr").find('td:eq(0)').text()); 
	$("#cardNumber").val($(this).closest("tr").find('td:eq(1)').text());
	$("#nameOfTheCard").val($(this).closest("tr").find('td:eq(2)').text());
	$("#validDate").val($(this).closest("tr").find('td:eq(3)').text());
	$("#cvcCode").val($(this).closest("tr").find('td:eq(4)').text());
	$("#amount").val($(this).closest("tr").find('td:eq(5)').text());
	$("#addr").val($(this).closest("tr").find('td:eq(6)').text());
	$("#pass").val($(this).closest("tr").find('td:eq(7)').text());

	$("#patinetId").show();
});


$(document).on("click", ".btnRemove", function (event) {
	
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");

	$.ajax(
			{
				url : "PatientAPI",
				type: "DELETE",
				data : "patID=" + $(this).data("patid") ,
				dataType :"text",
				complete : function(response, status)
				{
					onPatientDeleteComplete(response.responseText, status);
					console.log(response)
					console.log("***************************************************************")
					console.log(status)
				}
			})

})


function onPatientSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		console.log("results "+resultSet.status.trim());
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidPatSave").val("");
	$("#pat_Form")[0].reset();
}

function onPatientDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}



function validatePatientForm() {

	if($("#patientId").val().trim() == "")
	{
		console.log(nic)
		return "Invalid patient ID";
	}

	if($("#cardNumber").val().trim() == "")
	{
		return "Insert valid Card Number";
	}

	if($("#nameOfTheCard").val().trim() == "")
	{
		return "Insert valid Name of the card";
	}

	if($("#validDate").val().trim() == "")
	{
		return "Insert Valid Date";
	}

	if($("#cvcCode").val().trim() == "")
	{
		return "Insert Valid cvc code";
	}

	if($("#amount").val().trim() == "")
	{
		return "Insert amount";
	}

	if($("#pass").val().trim() == "")
	{
		return "Insert Password";
	}

	return true;
} 