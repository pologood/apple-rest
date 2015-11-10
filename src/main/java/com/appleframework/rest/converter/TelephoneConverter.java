/**
 * 版权声明： 版权所有 违者必究 2012
 * 日    期：12-6-8
 */
package com.appleframework.rest.converter;

import com.appleframework.rest.request.RestConverter;
import com.appleframework.rest.request.Telephone;

import org.springframework.util.StringUtils;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
public class TelephoneConverter implements RestConverter<String, Telephone> {

    @Override
    public Telephone convert(String source) {
        if (StringUtils.hasText(source)) {
            String zoneCode = source.substring(0, source.indexOf("-"));
            String telephoneCode = source.substring(source.indexOf("-") + 1);
            Telephone telephone = new Telephone();
            telephone.setZoneCode(zoneCode);
            telephone.setTelephoneCode(telephoneCode);
            return telephone;
        } else {
            return null;
        }
    }

    @Override
    public String unconvert(Telephone target) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getZoneCode());
        sb.append("-");
        sb.append(target.getTelephoneCode());
        return sb.toString();
    }

    @Override
    public Class<String> getSourceClass() {
        return String.class;
    }

    @Override
    public Class<Telephone> getTargetClass() {
        return Telephone.class;
    }
}

