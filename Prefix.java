/**
 * Converts a prefix expression into an infix expression, and evaluates it to a final answer
 * @author ajaykrishnavajjala
 */
import java.io.*; 
import java.util.*; 

public class Prefix {
	public static void solution(String[] prefixStr) {
		Deque<Integer> stack = new ArrayDeque<>();
        for(int i=prefixStr.length-1;i>-1;i--){
            String s = prefixStr[i];
            if(s.equals("")){
                continue;
            }
            if(s.equals("+")){
                stack.push(stack.poll()+stack.poll());
            }else if(s.equals("*")){
                stack.push(stack.poll() * stack.poll());
            }else if (s.equals("-")) {
            	 stack.push(stack.poll() - stack.poll());
            }
            else if (s.equals("@")) {
            	int[] array = new int[3];
            	for (int x = 0; x < 3; x++) {
            		array[x] = stack.poll();
            	}
            	if (array[0] > 0) {
            		stack.push(array[1]);
            	}
            	else {
            		stack.push(array[2]);
            	}
           }
            else{
                stack.push(Integer.parseInt(s));
            }
        }
        System.out.println(stack.poll());

	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner file = new Scanner(new File("/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Contest 4 Question/src/inputs.txt"));
		while (file.hasNextLine()) {
			String line = file.nextLine();
			solution(line.split(" "));
		}
		file.close();
	}

}
 