package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.PetBl;
import bo.edu.ucb.barkibu.bl.QuestionBl;
import bo.edu.ucb.barkibu.dto.CreatePetDto;
import bo.edu.ucb.barkibu.dto.CreateQuestionDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.QuitStrategy;
import java.util.Map;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;



@RestController
@RequestMapping("/v1/api/new-question")
public class QuestionApi {

    private final QuestionBl questionBl;

    public QuestionApi(QuestionBl questionBl) {
        this.questionBl = questionBl;
    }
@PostMapping()
    public ResponseEntity<ResponseDto<String>> createQuestion(@RequestHeader Map<String,String> headers, @RequestBody CreateQuestionDto createQuestionDto) {
        if (createQuestionDto.validate()){
            try {
                String jwt = AuthUtil.getTokenFromHeader(headers);
                String userName = AuthUtil.getUserNameFromToken(jwt);
                AuthUtil.verifyHasRole(jwt, "PUBLICAR PREGUNTA");
                questionBl.createQuestion(userName, createQuestionDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Question Publicated", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        }
        else {
            String statusCode = "SCTY-1001";
            ResponseDto<String> responseDto = new ResponseDto<>(null, statusCode, httpMessageUtilMap.get(statusCode).getMessage());
            System.out.print(createQuestionDto);
            return new ResponseEntity<>(responseDto, httpMessageUtilMap.get(statusCode).getHttpStatus());
        }
    }
}
