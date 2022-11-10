package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.RecoveryAccountBl;
import bo.edu.ucb.barkibu.dto.RecoveryAccountReqDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/recovery-account")
public class RecoveryAccountApi {
    RecoveryAccountBl recoveryAccountBl;

    public RecoveryAccountApi(RecoveryAccountBl recoveryAccountBl) {
        this.recoveryAccountBl = recoveryAccountBl;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<String>> createRecoveryAccount(@RequestBody RecoveryAccountReqDto recoveryAccountReqDto) {
        if (recoveryAccountReqDto.validate()) {
            try {
                recoveryAccountBl.createRecoveryAccount(recoveryAccountReqDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Security code sent to your email", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        } else {
            ResponseDto<String> responseDto = new ResponseDto<>(null, "SCTY-1001", "At least one field is empty");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }
}
