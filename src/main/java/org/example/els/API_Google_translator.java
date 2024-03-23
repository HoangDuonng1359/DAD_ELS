package org.example.els;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class API_Google_translator {
    public static String createNewToken(){
        return UUID.randomUUID().toString().toUpperCase();
    }
    public static String createURL(String sourceLangage, String targetLanguage , String textInput){
        StringBuilder str = new StringBuilder();
        str.append("http://translate.google.com/translate_a/t?client=gtrans&sl=");
        str.append(sourceLangage);
        str.append("&tl=");
        str.append(targetLanguage);
        str.append("&hl=");
        str.append(targetLanguage);
        str.append("&tk=");
        str.append(createNewToken());
        str.append("&q=");
        str.append(URLEncoder.encode(textInput, StandardCharsets.UTF_8));
        return str.toString();
    }
    private static String makeResult(StringBuilder input){
        input.delete(0,2);
        int len = input.length();
        input.deleteCharAt(len-1);
        input.deleteCharAt(len-2);
        return input.toString().replace("\\n","\n");
    }
    public static String translate(String textInput, String sourceLangage, String targetLanguage) throws IOException {
        String urlStr = createURL(sourceLangage, targetLanguage, textInput);
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return makeResult(response);
    }
    protected static ObservableList<String> listLANGUAGE = FXCollections.observableArrayList(
            "English",
            "Vietnamese",
            "Afrikaans",
            "Albanian",
            "Amharic",
            "Arabic",
            "Armenian",
            "Assamese",
            "Aymara",
            "Azerbaijani",
            "Bambara",
            "Basque",
            "Belarusian",
            "Bengali",
            "Bhojpuri",
            "Bosnian",
            "Bulgarian",
            "Catalan",
            "Cebuano",
            "Chichewa",
            "Chinese (Simplified)",
            "Chinese (Traditional)",
            "Corsican", "Croatian",
            "Czech", "Danish",
            "Dhivehi",
            "Dogri",
            "Dutch",
            "Esperanto",
            "Estonian",
            "Ewe",
            "Filipino",
            "Finnish",
            "French",
            "Frisian",
            "Galician",
            "Georgian",
            "German",
            "Greek",
            "Guarani",
            "Gujarati",
            "Haitian Creole",
            "Hausa",
            "Hawaiian",
            "Hebrew",
            "Hindi",
            "Hmong",
            "Hungarian",
            "Icelandic",
            "Igbo",
            "Ilocano",
            "Indonesian",
            "Irish",
            "Italian",
            "Japanese",
            "Javanese",
            "Kannada",
            "Kazakh",
            "Khmer",
            "Kinyarwanda",
            "Konkani",
            "Korean",
            "Krio",
            "Kurdish (Kurmanji)",
            "Kurdish (Sorani)",
            "Kyrgyz",
            "Lao",
            "Latin",
            "Latvian",
            "Lingala",
            "Lithuanian",
            "Luganda",
            "Luxembourgish",
            "Macedonian",
            "Maithili",
            "Malagasy",
            "Malay",
            "Malayalam",
            "Maltese",
            "Maori",
            "Marathi",
            "Meiteilon (Manipuri)",
            "Mizo", "Mongolian",
            "Myanmar (Burmese)",
            "Nepali", "Norwegian",
            "Odia (Oriya)",
            "Oromo",
            "Pashto",
            "Persian",
            "Polish",
            "Portuguese",
            "Punjabi",
            "Quechua",
            "Romanian",
            "Russian",
            "Samoan",
            "Sanskrit",
            "Scots Gaelic",
            "Sepedi",
            "Serbian",
            "Sesotho",
            "Shona",
            "Sindhi",
            "Sinhala",
            "Slovak",
            "Slovenian",
            "Somali",
            "Spanish",
            "Sundanese",
            "Swahili",
            "Swedish",
            "Tajik",
            "Tamil",
            "Tatar",
            "Telugu",
            "Thai",
            "Tigrinya",
            "Tsonga",
            "Turkish",
            "Turkmen",
            "Twi",
            "Ukrainian",
            "Urdu",
            "Uyghur",
            "Uzbek",
            "Welsh",
            "Xhosa",
            "Yiddish",
            "Yoruba",
            "Zulu");
    protected static String getLanguage(String in){
        switch (in){
            case "English":
                return "en";
            case "Afrikaans":
                return "af";
            case "Albanian":
                return "sq";
            case "Amharic":
                return "am";
            case "Arabic":
                return "ar";
            case "Armenian":
                return "hy";
            case "Azerbaijani":
                return "az";
            case "Basque":
                return "eu";
            case "Belarusian":
                return "be";
            case "Bengali":
                return "bn";
            case "Bosnian":
                return "bs";
            case "Bulgarian":
                return "bg";
            case "Catalan":
                return "ca";
            case "Cebuano":
                return "ceb";
            case "Chichewa":
                return "ny";
            case "Chinese (Simplified)":
                return "zh-CN";
            case "Chinese (Traditional)":
                return "zh-TW";
            case "Corsican":
                return "co";
            case "Croatian":
                return "hr";
            case "Czech":
                return "cs";
            case "Danish":
                return "da";
            case "Dutch":
                return "nl";
            case "Esperanto":
                return "eo";
            case "Estonian":
                return "et";
            case "Filipino":
                return "tl";
            case "Finnish":
                return "fi";
            case "French":
                return "fr";
            case "Frisian":
                return "fy";
            case "Galician":
                return "gl";
            case "Georgian":
                return "ka";
            case "German":
                return "de";
            case "Greek":
                return "el";
            case "Gujarati":
                return "gu";
            case "Haitian Creole":
                return "ht";
            case "Hausa":
                return "ha";
            case "Hawaiian":
                return "haw";
            case "Hebrew":
                return "iw";
            case "Hindi":
                return "hi";
            case "Hmong":
                return "hmn";
            case "Hungarian":
                return "hu";
            case "Icelandic":
                return "is";
            case "Igbo":
                return "ig";
            case "Indonesian":
                return "id";
            case "Irish":
                return "ga";
            case "Italian":
                return "it";
            case "Japanese":
                return "ja";
            case "Javanese":
                return "jw";
            case "Kannada":
                return "kn";
            case "Kazakh":
                return "kk";
            case "Khmer":
                return "km";
            case "Korean":
                return "ko";
            case "Kurdish (Kurmanji)":
                return "ku";
            case "Kyrgyz":
                return "ky";
            case "Lao":
                return "lo";
            case "Latin":
                return "la";
            case "Latvian":
                return "lv";
            case "Lithuanian":
                return "lt";
            case "Luxembourgish":
                return "lb";
            case "Macedonian":
                return "mk";
            case "Malagasy":
                return "mg";
            case "Malay":
                return "ms";
            case "Malayalam":
                return "ml";
            case "Maltese":
                return "mt";
            case "Maori":
                return "mi";
            case "Marathi":
                return "mr";
            case "Mongolian":
                return "mn";
            case "Myanmar (Burmese)":
                return "my";
            case "Nepali":
                return "ne";
            case "Norwegian":
                return "no";
            case "Pashto":
                return "ps";
            case "Persian":
                return "fa";
            case "Polish":
                return "pl";
            case "Portuguese":
                return "pt";
            case "Punjabi":
                return "pa";
            case "Romanian":
                return "ro";
            case "Russian":
                return "ru";
            case "Samoan":
                return "sm";
            case "Scots Gaelic":
                return "gd";
            case "Serbian":
                return "sr";
            case "Sesotho":
                return "st";
            case "Shona":
                return "sn";
            case "Sindhi":
                return "sd";
            case "Sinhala":
                return "si";
            case "Slovak":
                return "sk";
            case "Slovenian":
                return "sl";
            case "Somali":
                return "so";
            case "Spanish":
                return "es";
            case "Sundanese":
                return "su";
            case "Swahili":
                return "sw";
            case "Swedish":
                return "sv";
            case "Tajik":
                return "tg";
            case "Tamil":
                return "ta";
            case "Telugu":
                return "te";
            case "Thai":
                return "th";
            case "Turkish":
                return "tr";
            case "Ukrainian":
                return "uk";
            case "Urdu":
                return "ur";
            case "Uzbek":
                return "uz";
            case "Vietnamese":
                return "vi";
            case "Welsh":
                return "cy";
            case "Xhosa":
                return "xh";
            case "Yiddish":
                return "yi";
            case "Yoruba":
                return "yo";
            case "Zulu":
                return "zu";
            default:
                return "auto";
        }
    }

}
