package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.QuestionDetailBl;
import bo.edu.ucb.barkibu.dto.PetDataDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import bo.edu.ucb.barkibu.entity.VeterinarianAnswer;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/question")
public class QuestionDetailApi {
    QuestionDetailBl questionDetailBl;

    public QuestionDetailApi(QuestionDetailBl questionDetailBl) {
        this.questionDetailBl = questionDetailBl;
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<ResponseDto> getQuestionDetail(@RequestHeader Map<String,String> headers, @PathVariable Integer questionId) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            PetQuestion petQuestion = questionDetailBl.findPetQuestionByQuestionId(questionId);
            ResponseDto<PetQuestion> responseDto = new ResponseDto<>(petQuestion, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    @GetMapping("/{questionId}/pet-info")
    public ResponseEntity<ResponseDto> getQuestionPetInfo(@RequestHeader Map<String,String> headers, @PathVariable Integer questionId) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            PetDataDto petDataDto = questionDetailBl.findPetInfoByQuestionId(questionId);
            ResponseDto<PetDataDto> responseDto = new ResponseDto<>(petDataDto, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    @GetMapping("/{questionId}/answer")
    public ResponseEntity<ResponseDto> getQuestionAnswers(@RequestHeader Map<String,String> headers, @PathVariable Integer questionId) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            List<VeterinarianAnswer> veterinarianAnswers = questionDetailBl.findVeterinarianAnswersByQuestionId(questionId,userName);
            ResponseDto<List<VeterinarianAnswer>> responseDto = new ResponseDto<>(veterinarianAnswers, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

}
