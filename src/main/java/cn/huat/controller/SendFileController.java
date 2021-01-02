package cn.huat.controller;

import cn.huat.util.EasyResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: SendFileController <br/>
 * Description: <br/>
 * date: 2020/11/24 15:46<br/>
 *
 * @author suhd<br />
 */
@CrossOrigin
@RestController
@RequestMapping("/sFile")

public class SendFileController {
    public EasyResult saveSendFile(@RequestBody JSONObject param){

        return EasyResult.ok();
    }
}
