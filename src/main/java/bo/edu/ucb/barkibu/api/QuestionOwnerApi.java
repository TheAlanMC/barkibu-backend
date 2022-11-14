package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.QuestionOwnerBl;
import bo.edu.ucb.barkibu.bl.VeterinaryBl;
import bo.edu.ucb.barkibu.dto.QuestionOwnerDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.dto.VeterinaryDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/user/owner/question")
public class QuestionOwnerApi {
    private  QuestionOwnerBl questionOwnerBl;

    public QuestionOwnerApi(QuestionOwnerBl questionOwnerBl) {
        this.questionOwnerBl = questionOwnerBl;
    }

    @GetMapping("/{userName}")
    public ResponseEntity<ResponseDto> getVeterinaryByUserName(@RequestHeader Map<String,String> headers, @PathVariable String userName) {
        try {
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            QuestionOwnerDto questionOwnerDto = questionOwnerBl.findOwnerByUserName(userName);
            ResponseDto<QuestionOwnerDto> responseDto = new ResponseDto<>(questionOwnerDto, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }


}
