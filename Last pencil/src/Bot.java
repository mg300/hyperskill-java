public class Bot {
    public static int takePencil(int pencilsLeft){
        if(pencilsLeft==1) return 1;
        return switch (pencilsLeft % 4) {
            case 0 -> 3;
            case 1 -> (int) (Math.random() * 3 + 1);
            case 2 -> 1;
            case 3 -> 2;
            default -> 1;
        };
    }
}
