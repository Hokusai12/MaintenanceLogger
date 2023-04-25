package com.rapidprototypes.machinemaintenancelogger.services;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidprototypes.machinemaintenancelogger.models.Log;
import com.rapidprototypes.machinemaintenancelogger.models.MaintenanceItem;
import com.rapidprototypes.machinemaintenancelogger.models.Picture;
import com.rapidprototypes.machinemaintenancelogger.repositories.LogRepository;

@Service
public class LogService {
	
	@Autowired
	LogRepository repo;
	
	public List<Log> getAll() {
		return repo.findAll();
	}
	
	public Log findById(Long id) {
		Optional<Log> log = repo.findById(id);
		
		if(log.isPresent()) {
			return log.get();
		}
		
		return null;
	}
	/*
	 * 	<main class="bg-warning-subtle pt-5" style="min-height: 100vh">
		<div class="container mx-5 d-flex justify-content-around">
		<!-- Left column -->
			<div class="col-5">
				<div class="card border-warning border-3" style="width: 36rem">
					<div class="card-header bg-warning-subtle border-warning border-3">
						<h1><fmt:formatDate pattern="MMMM dd, yyyy" value="${log.createdAt}"/></h1>
					</div>
					<div class="card-body">
						<h3>${log.machine.name}</h3>
						<ul>
							<c:forEach var="maintenance" items="${log.maintenancePerformed}">
								<li>${maintenance.name}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<!-- Right column -->
			<div class="col-5 d-flex gap-2 flex-wrap justify-content-around">
				<c:forEach var="picture" items="${pictureList}">
					<img src="data:image/${picture.fileType};base64, ${picture.imageDataBase64}" style="height:300px;"/>
				</c:forEach>
			</div>
		</div>
	</main>
	 */
	
	public String getLogHTML(Log log) {
		Calendar cal = Calendar.getInstance();
		String currentDate = String.format("%d/%d/%d-%d:%d", cal.get(Calendar.MONTH), cal.get(Calendar.DATE), cal.get(Calendar.YEAR), cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE));
		
		String logHTML = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ\" crossorigin=\"anonymous\">\r\n"
				+ "</head>"
				+ "<body>"
				+ "<main class=\"bg-warning-subtle pt-5\" style=\"min-height: 100vh\">"
				+ "<div class=\"container mx-5 d-flex justify-content-around\">"
				+ "<div class=\"col-5\">"
				+ "<div class=\"card border-warning border-3\" style=\"width: 36rem\">"
				+ "<div class=\"card-header bg-warning-subtle border-warning border-3\">"
				+ "<h1>" + currentDate +"</h1>"
				+ "</div>"
				+ "<div class=\"card-body\">"
				+ "<h3>" + log.getMachine().getName() + "</h3>"
				+ "<ul>";
		
		if(log.getMaintenancePerformed() != null) {
			for(MaintenanceItem maintenance : log.getMaintenancePerformed()) {
				logHTML = logHTML.concat("<li>" + maintenance.getName() + "</li>");
			}
		}
		
		
		
		logHTML = logHTML.concat(
				"</ul>"
				+ "</div>"
				+ "</div>"
				+ "</div>"
				+ "<div class=\"col-5 d-flex gap-2 flex-wrap justify-content-around\">");
		
		if(log.getPictures() != null) {
			System.out.println("Images isn't null");
			for(Picture picture : log.getPictures()) {
				logHTML = logHTML.concat("<img src=\"" + picture.getFileName() + "\" style=\"height:300px;\"/>");
			}
		} else {
			System.out.println("Images is null");
		}
			
		logHTML = logHTML.concat("</div>"
				+ "</div>"
				+ "</main>"
				+ "</body>"
				+ "</html>");
		
		
		return logHTML;
	}
	
	public Log saveLog(Log newLog) {				
		return repo.save(newLog);
	}
}
