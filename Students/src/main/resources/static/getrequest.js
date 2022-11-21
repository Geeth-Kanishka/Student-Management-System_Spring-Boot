GET: $(document).ready(
	function() {

		// GET REQUEST
		$("#getAllStudents").click(function(event) {
			event.preventDefault();
			ajaxGet();
		});

		// DO GET
		function ajaxGet() {
			$.ajax({
				type: "GET",
				url: "api/getAllStudents",
				success: function(result,data, xhr) {
					if (xhr.status == 200) {
						$('#getResultDiv ul').empty();
						var custList = "";
						$.each(result,
							function(i, student) {
								var user = "Student Name =  "
									+ student.name
								

									+ "<br>";
								$('#getResultDiv .list-group').append(
									user)
							});
						console.log("Success: ", result);
					} else {
						$("#getResultDiv").html("<strong>Error</strong>");
						console.log("Fail: ", result);
					}
				},
				error: function(e) {
					$("#getResultDiv").html("<strong>Error</strong>");
					console.log("ERROR: ", e);
				}
			});
		}
	})