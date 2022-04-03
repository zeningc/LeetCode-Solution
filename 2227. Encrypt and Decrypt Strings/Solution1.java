class Encrypter {
    Map<Character, Integer> c2i;
    String[] values;
    Map<String, Integer> freq;
    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        this.values = values;
        c2i = new HashMap<>();
        for (int i = 0; i < keys.length; i++)   
            c2i.put(keys[i], i);
        freq = new HashMap<>();
        for (String d : dictionary)   {
            String encryptStr = encrypt(d);
            freq.put(encryptStr, freq.getOrDefault(encryptStr, 0) + 1);
        }
    }
    
    public String encrypt(String word1) {
        StringBuilder sb = new StringBuilder();
        for (char c : word1.toCharArray())  {
            sb.append(values[c2i.get(c)]);
        }
        return sb.toString();
    }
    
    public int decrypt(String word2) {
        return freq.getOrDefault(word2, 0);
    }
    
}
