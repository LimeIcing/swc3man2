package laace.swc3man2.services;

import laace.swc3man2.models.CourseModel;
import laace.swc3man2.models.StudentModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class StudentCourseService {

    //TODO
   /* public void postStudentCourseToAPI(CourseModel courseModel, StudentModel studentModel) {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request;
        ResponseEntity<String> response;

        headers.setContentType(MediaType.APPLICATION_JSON);
        map.add("course_id", "" + courseModel.getId());
        map.add("student_id", "" + studentModel.getId());
        request = new HttpEntity<>(map, headers);

        try {
            response = restTemplate.postForEntity(legacyURL + "/form", request, String.class);
            System.out.println(response.getStatusCode());
        } catch (HttpClientErrorException hCEE) {
            hCEE.printStackTrace();
        }
    }*/
}
