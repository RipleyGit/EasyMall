package me.seaOf.bean;

import lombok.Data;
import me.seaOf.exception.MsgException;
import me.seaOf.utils.WebUtils;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String password2;
    private String nickname;
    private String email;

    public void checkData() throws MsgException {
        // >>非空校验
        if (WebUtils.isNull(username)) {
            // 将提示消息存入request域中,通过转发将消息带到regist.jsp进行提示
            throw new MsgException("用户名不能为!");
        }
        if (WebUtils.isNull(password)) {
            // 将提示消息存入request域中,通过转发将消息带到regist.jsp进行提示
            throw new MsgException("密码不能为空!");
        }
        if (WebUtils.isNull(password2)) {
            // 将提示消息存入request域中,通过转发将消息带到regist.jsp进行提示
            throw new MsgException("确认密码不能为空!");
        }
        // >>两次密码是否一致校验
        if (!password.equals(password2)) {
            throw new MsgException("两次密码不一致!");
        }

        if (WebUtils.isNull(nickname)) {
            // 将提示消息存入request域中,通过转发将消息带到regist.jsp进行提示
            throw new MsgException("昵称不能为空!");
        }
        if (WebUtils.isNull(email)) {
            // 将提示消息存入request域中,通过转发将消息带到regist.jsp进行提示
            throw new MsgException("邮箱不能为空!");
        }
        // >>邮箱格式校验
        if (!email.matches("^\\w+@\\w+(\\.\\w+)+$")) {
            throw new MsgException("邮箱格式不正确!");
        }
    }
}
