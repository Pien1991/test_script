package other;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * Created by ShepardPin on 28/2/2018.
 */
public class tokenGenerator {

    public static void main(String[] args) throws Exception
    {
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC2oQhuiOeiWh81" + //
                "zf1UyhdaX/0eYKkmwBXeEzyskWeSU9FykEjkqvKY5Bd6nbJC4ROwCi6WBJxgjus+" + //
                "Xaan0ZuudeTBoo4rK+A54j1bv7FEiaA98axLOrIrqOCbC2kivqkOXcWorT0D7lzP" + //
                "K0B9FraQk2bXpedCT6yo7Q7VjJRNELcbNXpK7fa/ub7RrfVGBnGL7Vinsyu57vOU" + //
                "IvnnG2Gf7wt8Tln/Ncb8pEucol82fTOjtMVRE+8+SmvwtiHYWtKI5hpDcDm1leFe" + //
                "IyNifBSMoIQsH8Zs4wkepp7V0icQUZQNvstn05itDweSDJ5uxfdnmhncIQcuQv2Y" + //
                "lo50TKbXAgMBAAECggEBAI4ekqkx8M/uwgrk2hd5jOLPf/J71iZJpLrEzZqdLLQ6" + //
                "detpTNBmF5PTLFJHFcnlzaRYyBgGTor1rStDRcWi4DvLktD85xXBdySUYptbeV+U" + //
                "mhaaUzHX438GVINTReW76WM8pey1o53DiIXPasftPPUVQBrhCC4aQorSr5CPbXjq" + //
                "DpX2C2JbBSvZHvjhWemDCrqdxI29skY3HIaTEo/ORBjT8+lU4nve9uAv6paogSyV" + //
                "jgnidpbKl0k32hm25WP9IX2Qnq9mxJZ6Q/jW+daSdGu1zrAuH8iP5QxMejk85gSQ" + //
                "O6mKvS4dW01+k03eT4eKp8bNCCziHURgbDY8SqxrIYECgYEA8N3cT1I77Ho4md+w" + //
                "0tu5bgr0nI6AUCCz9mjdMzyUi8XTKzLEBPhpyVoHOFjn1tYURvGXU/ozpHcQtTZb" + //
                "ZszXgmKZ7fmXvzLCGZb7d7VB5IXmv5LFf30CB5roVrmVYCZJtIPuAdK1DsRRH/mR" + //
                "AGEwv9N0H1v2ie975eypVfbgw28CgYEAwhp4Cq7vcT1H6Bj6wEwyz2RWWDXwjwo5" + //
                "kddjC3C8oD9Dhv32DcjT2QQzaJrkx1EoGSb4DeeYdgxW+LKjqPW8Ol79CusCBVje" + //
                "fW6YIvRxq1tyCSgqnHAFZmhjiNrJP9v8EOHY8hizWllfVMzMljFulvGmSOrZ7HzA" + //
                "V9lXblYy/xkCgYB5HmcZL/XYDQGHRreI4FpXHoUwcGvPXxJ/lu2cRRtBScHNGCL/" + //
                "iNbRHEdDK1mxa0cZAYUiAk+TpReut92wTNMVynUy+G45A9IcwhJDeM7glgCZVrmf" + //
                "SQILRvcMHRgjGD252xmEqyrGeSt+E6W5diXoX/Elj73iUJ2R2baw9orBWwKBgGIQ" + //
                "KVGMa+AUC74Q0K5SUOFCWfUa2zwI+GjcHmfrPwDetWj/6U2xhoX/f1F739VrBQ0g" + //
                "CPZNaVNKjK384n48GCqNTt4zOMQmz9EjDK2sPblK6+QAbPa4CaJhXtgHYOFobILC" + //
                "923oWMRunqEjDsFam/qaf14beJnntO0tGuTdm8oRAoGAcKn9/QeDM8Aq6ZbrlsDT" + //
                "sfCivU2oYgG4WKkDB4igMMdCBArO7coIA/ArtiWNbAO3OyZRp03rgPP5kR8WHxJE" + //
                "MYBu/c4iWFof7onMP+279Y4pDndVYriVHsKIf04mFCl9RqgYO8Wd6FL3wqeXJD7W" + //
                "uuW5Np2oDE4brURfVjtHG54=";
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        String jwt = Jwts.builder().//
                setHeaderParam("alg", "RS256").//
                setHeaderParam("typ", "JWT").//
                setPayload("{\"foo\":\"foo\",\"bar\":\"bar\"}").//
                signWith(SignatureAlgorithm.RS256, kf.generatePrivate(spec)).//
                compact();
        String seg[] = jwt.split("\\.");
        System.out.println("jwt   : " + jwt);
        System.out.println("header: " + new String(Base64.getDecoder().decode(seg[0])));
        System.out.println("paylod: " + new String(Base64.getDecoder().decode(seg[1])));
    }
}
