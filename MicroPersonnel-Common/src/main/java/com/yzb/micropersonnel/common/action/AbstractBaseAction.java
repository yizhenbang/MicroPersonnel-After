package com.yzb.micropersonnel.common.action;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * ClassName: AbstractBaseAction
 * Description: 登录表单传入过来的值日期值进行一个格式化
 * date: 2021/12/9 12:08
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
public abstract class AbstractBaseAction {
    @Value("${yzb.date.pattern?:'yyyy-MM-dd HH:mm:ss'}")
    private String DATETIME_PATTERN;
    private final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    @InitBinder
    public void initWebDateBind(WebDataBinder binder){
        binder.registerCustomEditor(java.util.Date.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                LocalDateTime dateTime = LocalDateTime.parse(text,DATETIME_FORMATTER);
                Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                super.setValue(Date.from(instant));
            }
        });
    }
}
