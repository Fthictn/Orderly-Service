package com.orderly.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Messages {
    public static String LOGIN_SUCCEED = "Giriş işleminiz başarılı bir şekilde tamamlanmıştır.";
    public static String CONTROL_LOGIN_INFOS = "Kullanıcı bilgilerinizi kontrol ediniz!";
    public static String EMAIL_EXIST = "Bu email ile kayıtlı kullanıcı bulunmaktadır.";
    public static String REGISTER_SUCCEED = "Kaydınız başarılı bir şekilde tamamlanmıştır.";
    public static String USER_INFOS_UPDATED = "Kullanıcı bilgileri güncellenmiştir.";
    public static String PROJECT_CREATED_SUCCEED = "Projeniz başarılı bir şekilde oluşturulmuştur.";
    public static String PROJECT_EXIST = "Bu proje adında bir kayıt zaten mevcuttur.";
    public static String GENERAL_SUCCEED_MESSAGE = "İşleminiz başarılı bir şekilde tamamlanmıştır.";
}
