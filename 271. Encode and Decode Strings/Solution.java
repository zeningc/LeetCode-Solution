public class Codec {
    char splitSign = (char)257;
    char emptySign = (char)258;
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            if (str.length() == 0)
                sb.append(emptySign);
            for (char c : str.toCharArray())    {
                sb.append(c);
            }
            sb.append(splitSign);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ret = new LinkedList<>();
        int n = s.length();
        String[] split = s.split(String.valueOf(splitSign));
        for (String splitStr : split)   {
            if (splitStr.length() == 1 && splitStr.equals(String.valueOf(emptySign)))
                ret.add("");
            else
                ret.add(splitStr);
        }
        
        return ret;
    }
}