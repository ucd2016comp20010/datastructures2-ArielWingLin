Q2: 
Goal: Implement Queue with 2 stacks 

Step.1: Form 2 new stacks
Stack<E> s1 = new Stack<>();  #Assume that this will become the Queue - enqueue
Stack<E> s2 = new Stack<>();  #This will be added to s1 - dequeue

Step.2: Method for pushing onto s1;
For Enqueuing: 
s1.push(item);

Step3: Method for dequeuing: 
1) if both s1.isEmpty() && s2.isEmpty() == true
   -> return null / throw Error(Stack(s) is/are empty :( );
   
2) if either s.isEmpty() || s.isEmpty() == true
   Then System.out.println(A stack is empty!(therefore, there already is a Queue!));
   return null;

3) If Stack2 is not empty 
   while (!stack2.isEmpty())) {
   E element = s2.pop();
   s1.push(element);
   }


Q3: 
Goal: reversing a Stack in place with 2 other stack 
Before it starts, include the method peek() to check the top element of a stack

Step.1: Initialize the required stacks
Stack<E> Og = new Stack<>();       # Original Stack (E.g 3, 2, 1)
Stack<E> temp1 = new Stack<>();    # Temp stack to store (1, 2, 3)
Stack<E> temp2 = new Stack<>();    # Another temp stack to store (3, 2, 1)
                                     -> then reverse it again to Original -> (1, 2, 3)

Step.2: Move from Og to temp1
While(!Og.isEmpty()) {
  temp1.push(Og.pop());
}
Stack: 3, 2, 1 -> 1, 2, 3

Step.3: Move from temp1 to temp2
While(!temp1.isEmpty()){
  temp2.push(temp1.pop());
}
Stack: 1, 2, 3 -> 3, 2, 1

Step.4: Finally, reversing the Og Stack using temp2
While(!temp2.isEmpty()){
  Og.push(temp2.pop());
}
Stack: 3, 2, 1 -> 1, 2, 3

Extension to Q4: 
If it is asked for other bases, replace 2 with 10 or 16, 
How to deal with bases greater than 9? 
A: 
1. Enum would be possible but will be overcomplicated
2. Better solution is the use of ASCII
   For example: Hexdecimal - 300 
   - 300 / 16 = 18 .. rem = 12
   Then use a if statement: if (remainder >= 10 && remainder <= 15)
   - digiToChar = (char) ('0' + remainder)
   - > This returns c
   