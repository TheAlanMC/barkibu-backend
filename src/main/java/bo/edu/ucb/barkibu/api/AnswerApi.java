package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.AnswerBl;
import bo.edu.ucb.barkibu.dto.AnswerDto;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;

@RestController
@RequestMapping("/v1/api/answer")
public class AnswerApi {
    private final AnswerBl answerBl;

    public AnswerApi(AnswerBl answerBl) {
        this.answerBl = answerBl;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<String>> createAnswer(@RequestHeader Map<String,String> headers, @RequestBody AnswerDto answerDto) {
        if (answerDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                String username = AuthUtil.getUserNameFromToken(jwt);
                AuthUtil.verifyHasRole(jwt, "PUBLICAR RESPUESTA");
               answerBl.createAnswer(username, answerDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Answer Created", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        }
        else {
            String statusCode = "SCTY-1001";
            ResponseDto<String> responseDto = new ResponseDto<>(null, statusCode, httpMessageUtilMap.get(statusCode).getMessage());
            return new ResponseEntity<>(responseDto, httpMessageUtilMap.get(statusCode).getHttpStatus());
        }
    }

    @PutMapping()
    public ResponseEntity<ResponseDto<String>> updateAnswer(@RequestHeader Map<String,String> headers, @RequestBody AnswerDto answerDto) {
        if (answerDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                String username = AuthUtil.getUserNameFromToken(jwt);
                AuthUtil.verifyHasRole(jwt, "EDITAR RESPUESTA");
               answerBl.updateAnswer(username, answerDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Answer Updated", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        }
        else {
            String statusCode = "SCTY-1001";
            ResponseDto<String> responseDto = new ResponseDto<>(null, statusCode, httpMessageUtilMap.get(statusCode).getMessage());
            return new ResponseEntity<>(responseDto, httpMessageUtilMap.get(statusCode).getHttpStatus());
        }
    }

    @GetMapping("/{#answerId}/support")
    public ResponseEntity<ResponseDto<String>> likeAnswer(@RequestHeader Map<String,String> headers ,@PathVariable Integer answerId) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                String username = AuthUtil.getUserNameFromToken(jwt);
                answerBl.likeAnswer(username, answerId);
                ResponseDto<String> responseDto = new ResponseDto<>("Answer Liked", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
    }

}
