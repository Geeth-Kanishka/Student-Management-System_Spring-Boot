$(document).ready(
	function() {

		// SUBMIT FORM
		$("#Form1").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxPost();
		});

		function ajaxPost() {

			// PREPARE FORM DATA
			var formData = {
				name: $("#name").val(),
				email: $("#email").val(),
				age: $("#age").val(),
				score: $("#score").val()
			}

			// DO POST
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "api/saveStudent",
				data: JSON.stringify(formData),
				dataType: 'json',
				success: function(data, textStatus, xhr) {
					if (xhr.status == 201) {
						$("#postResultDiv").html(
							"" 
							+ "Student added <br>"
							);
					} else {
						$("#postResultDiv").html("<strong>Error</strong>");
					}
					console.log(xhr.status);
				},
				error: function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
			});

		}

	})