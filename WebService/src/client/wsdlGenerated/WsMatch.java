
package client.wsdlGenerated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für wsMatch complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="wsMatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="team1" type="{http://Server/}wsTeam" minOccurs="0"/>
 *         &lt;element name="team2" type="{http://Server/}wsTeam" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsMatch", propOrder = {
    "team1",
    "team2"
})
public class WsMatch {

    protected WsTeam team1;
    protected WsTeam team2;

    /**
     * Ruft den Wert der team1-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link WsTeam }
     *     
     */
    public WsTeam getTeam1() {
        return team1;
    }

    /**
     * Legt den Wert der team1-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link WsTeam }
     *     
     */
    public void setTeam1(WsTeam value) {
        this.team1 = value;
    }

    /**
     * Ruft den Wert der team2-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link WsTeam }
     *     
     */
    public WsTeam getTeam2() {
        return team2;
    }

    /**
     * Legt den Wert der team2-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link WsTeam }
     *     
     */
    public void setTeam2(WsTeam value) {
        this.team2 = value;
    }

}
