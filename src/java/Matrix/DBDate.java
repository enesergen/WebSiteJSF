package Matrix;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/*
bu class db için timestamp türünde anlık saat tutuyor 
class içindeki static fonksiyon nesne new yapmadan çalışıyor
DBDate.dbdate() şeklinde anlık tarih bilgisini 2021-05-09 21:55:14.564
formatında üretiyor 
db insert işlemlerinde ortak kullanılabilir.
*/

public class DBDate {

    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Timestamp dbDate() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    return timestamp;
    }
}
