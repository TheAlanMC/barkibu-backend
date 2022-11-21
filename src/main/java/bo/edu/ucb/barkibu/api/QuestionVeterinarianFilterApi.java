package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.QuestionVeterinarianFilterBl;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.dto.QuestionVeterinarianFilterDto;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;

@RestController
@RequestMapping("/v1/api/question/veterinarian-filter")
public class QuestionVeterinarianFilterApi {
    QuestionVeterinarianFilterBl questionVeterinarianFilterBl;

    public QuestionVeterinarianFilterApi(QuestionVeterinarianFilterBl questionVeterinarianFilterBl) {
        this.questionVeterinarianFilterBl = questionVeterinarianFilterBl;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto> getVeterinarianQuestion(@RequestHeader Map<String,String> headers, @RequestBody QuestionVeterinarianFilterDto questionVeterinarianFilterDto, @RequestParam(defaultValue= "1") Integer page ,@RequestParam(defaultValue= "10", required = false) Integer pageSize ) {
        if(questionVeterinarianFilterDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                AuthUtil.getUserNameFromToken(jwt);
                Pageable pageable = PageRequest.of(page - 1, pageSize);
                List<PetQuestion> petQuestions =questionVeterinarianFilterBl.findPetQuestionsByVeterinarianFilter(questionVeterinarianFilterDto, pageable);
                ResponseDto<List<PetQuestion>> responseDto = new ResponseDto<>(petQuestions, "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        } else {
            String statusCode = "SCTY-1001";
            ResponseDto<String> responseDto = new ResponseDto<>(null, statusCode, httpMessageUtilMap.get(statusCode).getMessage());
            return new ResponseEntity<>(responseDto, httpMessageUtilMap.get(statusCode).getHttpStatus());
        }
    }
}
