
package client.wsdlGenerated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für wsTournament complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="wsTournament">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="matches" type="{http://Server/}wsMatch" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tournamentName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTournament", propOrder = {
    "matches",
    "tournamentName"
})
public class WsTournament {

    @XmlElement(nillable = true)
    protected List<WsMatch> matches;
    protected String tournamentName;

    /**
     * Gets the value of the matches property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the matches property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMatches().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsMatch }
     * 
     * 
     */
    public List<WsMatch> getMatches() {
        if (matches == null) {
            matches = new ArrayList<WsMatch>();
        }
        return this.matches;
    }

    /**
     * Ruft den Wert der tournamentName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTournamentName() {
        return tournamentName;
    }

    /**
     * Legt den Wert der tournamentName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTournamentName(String value) {
        this.tournamentName = value;
    }

}
