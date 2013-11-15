//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.23 at 04:09:58 PM EDT 
//


package org.energyos.espi.common.domain;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GrantType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GrantType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="authorization_code"/>
 *     &lt;enumeration value="client_credentials"/>
 *     &lt;enumeration value="refresh_token"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GrantType")
@XmlEnum
public enum GrantType {

    @XmlEnumValue("authorization_code")
    AUTHORIZATION_CODE("authorization_code"),
    @XmlEnumValue("client_credentials")
    CLIENT_CREDENTIALS("client_credentials"),
    @XmlEnumValue("refresh_token")
    REFRESH_TOKEN("refresh_token");
    private final String value;

    GrantType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GrantType fromValue(String v) {
        for (GrantType c: GrantType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
