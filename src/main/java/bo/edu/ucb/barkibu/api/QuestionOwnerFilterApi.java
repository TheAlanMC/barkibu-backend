package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.QuestionOwnerFilterBl;
import bo.edu.ucb.barkibu.bl.QuestionVeterinarianFilterBl;
import bo.edu.ucb.barkibu.dto.QuestionVeterinarianFilterDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;

@RestController
@RequestMapping("/v1/api/question/owner-filter")
public class QuestionOwnerFilterApi {
    QuestionOwnerFilterBl questionOwnerFilterBl;

    public QuestionOwnerFilterApi(QuestionOwnerFilterBl questionOwnerFilterBl) {
        this.questionOwnerFilterBl = questionOwnerFilterBl;
    }

    // TODO: REMOVE BODY FROM GET REQUEST
    @GetMapping()
    public ResponseEntity<ResponseDto> getVeterinarianQuestion(@RequestHeader Map<String, String> headers,
            @RequestBody QuestionVeterinarianFilterDto questionVeterinarianFilterDto) {
        if (questionVeterinarianFilterDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                AuthUtil.getUserNameFromToken(jwt);
                List<PetQuestion> petQuestions = questionOwnerFilterBl
                        .findPetQuestionByKeyWord(questionVeterinarianFilterDto);
                ResponseDto<List<PetQuestion>> responseDto = new ResponseDto<>(petQuestions, "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        } else {
            String statusCode = "SCTY-1001";
            ResponseDto<String> responseDto = new ResponseDto<>(null, statusCode,
                    httpMessageUtilMap.get(statusCode).getMessage());
            return new ResponseEntity<>(responseDto, httpMessageUtilMap.get(statusCode).getHttpStatus());
        }
    }
}
