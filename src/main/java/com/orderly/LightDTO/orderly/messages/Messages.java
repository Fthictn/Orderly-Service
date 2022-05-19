package com.orderly.LightDTO.orderly.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Messages {
    public static final String LOGIN_SUCCEED = "Giriş işleminiz başarılı bir şekilde tamamlanmıştır.";
    public static final String CONTROL_LOGIN_INFOS = "Kullanıcı bilgilerinizi kontrol ediniz!";
    public static final String EMAIL_EXIST = "Bu email ile kayıtlı kullanıcı bulunmaktadır.";
    public static final String REGISTER_SUCCEED = "Kaydınız başarılı bir şekilde tamamlanmıştır.";
    public static final String USER_INFOS_UPDATED = "Kullanıcı bilgileri güncellenmiştir.";
    public static final String PROJECT_CREATED_SUCCEED = "Projeniz başarılı bir şekilde oluşturulmuştur.";
    public static final String PROJECT_EXIST = "Bu proje adında bir kayıt zaten mevcuttur.";
    public static final String GENERAL_SUCCEED_MESSAGE = "İşleminiz başarılı bir şekilde tamamlanmıştır.";
}
