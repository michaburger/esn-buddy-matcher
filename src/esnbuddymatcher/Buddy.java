/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esnbuddymatcher;

/**
 *
 * @author michaburger
 */
public class Buddy {
    
    
    
    private String firstName;
    private String lastName;
    private String country;
    private String mail;
    private String phone;
    private int age;
    private int[] interests;
    private String[] languages;
    private String facebook;
    private String studyLevel;
    
    
    public Buddy(String firstName, String lastName, String country, String mail,
                    String phone, int age, int[] interests, String facebook, String studyLevel, String languages){
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.mail = mail;
        this.phone = phone;
        this.age = age;
        this.facebook = facebook;
        this.studyLevel = studyLevel;
        this.interests = interests.clone();
        //TODO: decode languages
        
    }
    
    public void addLanguage(String lang){
        languages[languages.length] = lang;
    }
    
    public String mail() {return mail;}
    public int age() {return age;}
    public int[] interests() {return interests;}
    public String country() {return country;}
    
    //print content for debug reasons
    public void print(){
        String output = "";
        
        output += "\nFirst name: "+firstName;
        output += "\nLast name: "+lastName;
        output += "\nCountry: "+country;
        output += "\nMail: "+mail;
        output += "\nPhone: "+phone;
        output += "\nAge: "+age;
        output += "\nLanguages: "+languages;
        output += "\nStudy level: "+studyLevel;
        output += "\nFacebook: "+facebook;
        
        String intr = "\nInterests: ";
        for (int i=0; i<interests.length; i++){
            intr += (interests[i]+",");
        }
        
        output += intr;
        
        System.out.println(output);
    }
    
    //lookup table for country codes
    protected String shortCountryFormat(String country){
        
        if(country.contains("Afghanistan")) return "AF"; 
        if(country.contains("Albania")) return "AL"; 
        if(country.contains("Algeria")) return "DZ"; 
        if(country.contains("American Samoa")) return "AS"; 
        if(country.contains("Andorra")) return "AD"; 
        if(country.contains("Angola")) return "AO"; 
        if(country.contains("Anguilla")) return "AI"; 
        if(country.contains("Antarctica")) return "AQ"; 
        if(country.contains("Antigua And Barbuda")) return "AG"; 
        if(country.contains("Argentina")) return "AR"; 
        if(country.contains("Armenia")) return "AM"; 
        if(country.contains("Aruba")) return "AW"; 
        if(country.contains("Australia")) return "AU"; 
        if(country.contains("Austria")) return "AT"; 
        if(country.contains("Azerbaijan")) return "AZ"; 
        if(country.contains("Bahamas")) return "BS"; 
        if(country.contains("Bahrain")) return "BH"; 
        if(country.contains("Bangladesh")) return "BD"; 
        if(country.contains("Barbados")) return "BB"; 
        if(country.contains("Belarus")) return "BY"; 
        if(country.contains("Belgium")) return "BE"; 
        if(country.contains("Belize")) return "BZ"; 
        if(country.contains("Benin")) return "BJ"; 
        if(country.contains("Bermuda")) return "BM"; 
        if(country.contains("Bhutan")) return "BT"; 
        if(country.contains("Bolivia")) return "BO"; 
        if(country.contains("Bosnia And Herzegovina")) return "BA"; 
        if(country.contains("Botswana")) return "BW"; 
        if(country.contains("Bouvet Island")) return "BV"; 
        if(country.contains("Brazil")) return "BR"; 
        if(country.contains("British Indian Ocean Territory")) return "IO"; 
        if(country.contains("Brunei Darussalam")) return "BN"; 
        if(country.contains("Bulgaria")) return "BG"; 
        if(country.contains("Burkina Faso")) return "BF"; 
        if(country.contains("Burundi")) return "BI"; 
        if(country.contains("Cambodia")) return "KH"; 
        if(country.contains("Cameroon")) return "CM"; 
        if(country.contains("Canada")) return "CA"; 
        if(country.contains("Cape Verde")) return "CV"; 
        if(country.contains("Cayman Islands")) return "KY"; 
        if(country.contains("Central African Republic")) return "CF"; 
        if(country.contains("Chad")) return "TD"; 
        if(country.contains("Chile")) return "CL"; 
        if(country.contains("China")) return "CN"; 
        if(country.contains("Christmas Island")) return "CX"; 
        if(country.contains("Cocos (keeling) Islands")) return "CC"; 
        if(country.contains("Colombia")) return "CO"; 
        if(country.contains("Comoros")) return "KM"; 
        if(country.contains("Congo")) return "CG"; 
        if(country.contains("Congo, The Democratic Republic Of The")) return "CD"; 
        if(country.contains("Cook Islands")) return "CK"; 
        if(country.contains("Costa Rica")) return "CR"; 
        if(country.contains("Cote D'ivoire")) return "CI"; 
        if(country.contains("Croatia")) return "HR"; 
        if(country.contains("Cuba")) return "CU"; 
        if(country.contains("Cyprus")) return "CY"; 
        if(country.contains("Czech Republic")) return "CZ"; 
        if(country.contains("Denmark")) return "DK"; 
        if(country.contains("Djibouti")) return "DJ"; 
        if(country.contains("Dominica")) return "DM"; 
        if(country.contains("Dominican Republic")) return "DO"; 
        if(country.contains("East Timor")) return "TP"; 
        if(country.contains("Ecuador")) return "EC"; 
        if(country.contains("Egypt")) return "EG"; 
        if(country.contains("El Salvador")) return "SV"; 
        if(country.contains("Equatorial Guinea")) return "GQ"; 
        if(country.contains("Eritrea")) return "ER"; 
        if(country.contains("Estonia")) return "EE"; 
        if(country.contains("Ethiopia")) return "ET"; 
        if(country.contains("Falkland Islands (malvinas)")) return "FK"; 
        if(country.contains("Faroe Islands")) return "FO"; 
        if(country.contains("Fiji")) return "FJ"; 
        if(country.contains("Finland")) return "FI"; 
        if(country.contains("France")) return "FR"; 
        if(country.contains("French Guiana")) return "GF"; 
        if(country.contains("French Polynesia")) return "PF"; 
        if(country.contains("French Southern Territories")) return "TF"; 
        if(country.contains("Gabon")) return "GA"; 
        if(country.contains("Gambia")) return "GM"; 
        if(country.contains("Georgia")) return "GE"; 
        if(country.contains("Germany")) return "DE"; 
        if(country.contains("Ghana")) return "GH"; 
        if(country.contains("Gibraltar")) return "GI"; 
        if(country.contains("Greece")) return "GR"; 
        if(country.contains("Greenland")) return "GL"; 
        if(country.contains("Grenada")) return "GD"; 
        if(country.contains("Guadeloupe")) return "GP"; 
        if(country.contains("Guam")) return "GU"; 
        if(country.contains("Guatemala")) return "GT"; 
        if(country.contains("Guinea")) return "GN"; 
        if(country.contains("Guinea-bissau")) return "GW"; 
        if(country.contains("Guyana")) return "GY"; 
        if(country.contains("Haiti")) return "HT"; 
        if(country.contains("Heard Island And Mcdonald Islands")) return "HM"; 
        if(country.contains("Holy See (vatican City State)")) return "VA"; 
        if(country.contains("Honduras")) return "HN"; 
        if(country.contains("Hong Kong")) return "HK"; 
        if(country.contains("Hungary")) return "HU"; 
        if(country.contains("Iceland")) return "IS"; 
        if(country.contains("India")) return "IN"; 
        if(country.contains("Indonesia")) return "ID"; 
        if(country.contains("Iran, Islamic Republic Of")) return "IR"; 
        if(country.contains("Iraq")) return "IQ"; 
        if(country.contains("Ireland")) return "IE"; 
        if(country.contains("Israel")) return "IL"; 
        if(country.contains("Italy")) return "IT"; 
        if(country.contains("Jamaica")) return "JM"; 
        if(country.contains("Japan")) return "JP"; 
        if(country.contains("Jordan")) return "JO"; 
        if(country.contains("Kazakstan")) return "KZ"; 
        if(country.contains("Kenya")) return "KE"; 
        if(country.contains("Kiribati")) return "KI"; 
        if(country.contains("Korea, Democratic People's Republic Of")) return "KP"; 
        if(country.contains("Korea, Republic Of")) return "KR"; 
        if(country.contains("Kosovo")) return "KV"; 
        if(country.contains("Kuwait")) return "KW"; 
        if(country.contains("Kyrgyzstan")) return "KG"; 
        if(country.contains("Lao People's Democratic Republic")) return "LA"; 
        if(country.contains("Latvia")) return "LV"; 
        if(country.contains("Lebanon")) return "LB"; 
        if(country.contains("Lesotho")) return "LS"; 
        if(country.contains("Liberia")) return "LR"; 
        if(country.contains("Libyan Arab Jamahiriya")) return "LY"; 
        if(country.contains("Liechtenstein")) return "LI"; 
        if(country.contains("Lithuania")) return "LT"; 
        if(country.contains("Luxembourg")) return "LU"; 
        if(country.contains("Macau")) return "MO"; 
        if(country.contains("Macedonia, The Former Yugoslav Republic Of")) return "MK"; 
        if(country.contains("Madagascar")) return "MG"; 
        if(country.contains("Malawi")) return "MW"; 
        if(country.contains("Malaysia")) return "MY"; 
        if(country.contains("Maldives")) return "MV"; 
        if(country.contains("Mali")) return "ML"; 
        if(country.contains("Malta")) return "MT"; 
        if(country.contains("Marshall Islands")) return "MH"; 
        if(country.contains("Martinique")) return "MQ"; 
        if(country.contains("Mauritania")) return "MR"; 
        if(country.contains("Mauritius")) return "MU"; 
        if(country.contains("Mayotte")) return "YT"; 
        if(country.contains("Mexico")) return "MX"; 
        if(country.contains("Micronesia, Federated States Of")) return "FM"; 
        if(country.contains("Moldova, Republic Of")) return "MD"; 
        if(country.contains("Monaco")) return "MC"; 
        if(country.contains("Mongolia")) return "MN"; 
        if(country.contains("Montserrat")) return "MS"; 
        if(country.contains("Montenegro")) return "ME"; 
        if(country.contains("Morocco")) return "MA"; 
        if(country.contains("Mozambique")) return "MZ"; 
        if(country.contains("Myanmar")) return "MM"; 
        if(country.contains("Namibia")) return "NA"; 
        if(country.contains("Nauru")) return "NR"; 
        if(country.contains("Nepal")) return "NP"; 
        if(country.contains("Netherlands")) return "NL"; 
        if(country.contains("Netherlands Antilles")) return "AN"; 
        if(country.contains("New Caledonia")) return "NC"; 
        if(country.contains("New Zealand")) return "NZ"; 
        if(country.contains("Nicaragua")) return "NI"; 
        if(country.contains("Niger")) return "NE"; 
        if(country.contains("Nigeria")) return "NG"; 
        if(country.contains("Niue")) return "NU"; 
        if(country.contains("Norfolk Island")) return "NF"; 
        if(country.contains("Northern Mariana Islands")) return "MP"; 
        if(country.contains("Norway")) return "NO"; 
        if(country.contains("Oman")) return "OM"; 
        if(country.contains("Pakistan")) return "PK"; 
        if(country.contains("Palau")) return "PW"; 
        if(country.contains("Palestinian Territory, Occupied")) return "PS"; 
        if(country.contains("Panama")) return "PA"; 
        if(country.contains("Papua New Guinea")) return "PG"; 
        if(country.contains("Paraguay")) return "PY"; 
        if(country.contains("Peru")) return "PE"; 
        if(country.contains("Philippines")) return "PH"; 
        if(country.contains("Pitcairn")) return "PN"; 
        if(country.contains("Poland")) return "PL"; 
        if(country.contains("Portugal")) return "PT"; 
        if(country.contains("Puerto Rico")) return "PR"; 
        if(country.contains("Qatar")) return "QA"; 
        if(country.contains("Reunion")) return "RE"; 
        if(country.contains("Romania")) return "RO"; 
        if(country.contains("Russian Federation")) return "RU"; 
        if(country.contains("Rwanda")) return "RW"; 
        if(country.contains("Saint Helena")) return "SH"; 
        if(country.contains("Saint Kitts And Nevis")) return "KN"; 
        if(country.contains("Saint Lucia")) return "LC"; 
        if(country.contains("Saint Pierre And Miquelon")) return "PM"; 
        if(country.contains("Saint Vincent And The Grenadines")) return "VC"; 
        if(country.contains("Samoa")) return "WS"; 
        if(country.contains("San Marino")) return "SM"; 
        if(country.contains("Sao Tome And Principe")) return "ST"; 
        if(country.contains("Saudi Arabia")) return "SA"; 
        if(country.contains("Senegal")) return "SN"; 
        if(country.contains("Serbia")) return "RS"; 
        if(country.contains("Seychelles")) return "SC"; 
        if(country.contains("Sierra Leone")) return "SL"; 
        if(country.contains("Singapore")) return "SG"; 
        if(country.contains("Slovakia")) return "SK"; 
        if(country.contains("Slovenia")) return "SI"; 
        if(country.contains("Solomon Islands")) return "SB"; 
        if(country.contains("Somalia")) return "SO"; 
        if(country.contains("South Africa")) return "ZA"; 
        if(country.contains("South Georgia And The South Sandwich Islands")) return "GS"; 
        if(country.contains("Spain")) return "ES"; 
        if(country.contains("Sri Lanka")) return "LK"; 
        if(country.contains("Sudan")) return "SD"; 
        if(country.contains("Suriname")) return "SR"; 
        if(country.contains("Svalbard And Jan Mayen")) return "SJ"; 
        if(country.contains("Swaziland")) return "SZ"; 
        if(country.contains("Sweden")) return "SE"; 
        if(country.contains("Switzerland")) return "CH"; 
        if(country.contains("Syrian Arab Republic")) return "SY"; 
        if(country.contains("Taiwan, Province Of China")) return "TW"; 
        if(country.contains("Tajikistan")) return "TJ"; 
        if(country.contains("Tanzania, United Republic Of")) return "TZ"; 
        if(country.contains("Thailand")) return "TH"; 
        if(country.contains("Togo")) return "TG"; 
        if(country.contains("Tokelau")) return "TK"; 
        if(country.contains("Tonga")) return "TO"; 
        if(country.contains("Trinidad And Tobago")) return "TT"; 
        if(country.contains("Tunisia")) return "TN"; 
        if(country.contains("Turkey")) return "TR"; 
        if(country.contains("Turkmenistan")) return "TM"; 
        if(country.contains("Turks And Caicos Islands")) return "TC"; 
        if(country.contains("Tuvalu")) return "TV"; 
        if(country.contains("Uganda")) return "UG"; 
        if(country.contains("Ukraine")) return "UA"; 
        if(country.contains("United Arab Emirates")) return "AE"; 
        if(country.contains("United Kingdom")) return "GB"; 
        if(country.contains("United States")) return "US"; 
        if(country.contains("United States Minor Outlying Islands")) return "UM"; 
        if(country.contains("Uruguay")) return "UY"; 
        if(country.contains("Uzbekistan")) return "UZ"; 
        if(country.contains("Vanuatu")) return "VU"; 
        if(country.contains("Venezuela")) return "VE"; 
        if(country.contains("Viet Nam")) return "VN"; 
        if(country.contains("Virgin Islands, British")) return "VG"; 
        if(country.contains("Virgin Islands, U.s.")) return "VI"; 
        if(country.contains("Wallis And Futuna")) return "WF"; 
        if(country.contains("Western Sahara")) return "EH"; 
        if(country.contains("Yemen")) return "YE"; 
        if(country.contains("Zambia")) return "ZM"; 
        if(country.contains("Zimbabwe")) return "ZW";

        return "XX";
    }
    
    //give index n of the element that has to be swapped with the element n-1
    protected int[] swapEntries(int[] array, int n){
        if(n == 0) return array;
        
        int buffer = array[n-1];
        array[n-1] = array[n];
        array[n] = buffer;
        return array;
        
    }
    
    //give index n of the element that has to be swapped with the element n-1
    protected double[] swapEntries(double[] array, int n){
        if(n == 0) return array;
        
        double buffer = array[n-1];
        array[n-1] = array[n];
        array[n] = buffer;
        return array;
        
    }
    
    
    
    protected String getOutputFormat(String fs){
        String output = "";
        output += firstName + fs;
        output += lastName + fs;
        output += country + fs;
        output += mail + fs;
        output += phone + fs;
        output += facebook + fs;
        return output;
    }
    
}
