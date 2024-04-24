import java.util.HashMap;

public class Mirror {

    private static final HashMap<Character, Character> charMap = new HashMap<>();

    static {
        charMap.put('<', '>');
        charMap.put('[', ']');
        charMap.put('{', '}');
        charMap.put('(', ')');
        charMap.put('/', '\\');
    }
    public static String mirrorLine(String line){
        StringBuilder mirroredLine = new StringBuilder();
        for (char character : line.toCharArray()){
            if(charMap.containsKey(character)){
                mirroredLine.append(charMap.get(character));

            }else if(charMap.containsValue(character)){
                for(char c : charMap.keySet()){
                    if(charMap.get(c)==character){
                        mirroredLine.append(c);
                    }
                }
            }
            else{
                mirroredLine.append(character);
            }

        }
        mirroredLine.reverse();

        return mirroredLine.toString();
    }
}
// < to >, [ to ], { to }, ( to ), / to \,