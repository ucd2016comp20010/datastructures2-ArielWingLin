Practical: Stacks + Queues
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
   

Practical: Trees 1
Q2: 
To start off, a recursive method is formed - public int countExternalB(tree, p)
tree: The binary tree you would like to count its external nodes
p: Specific position in tree
And this method returns the total number of external nodes of a tree

if T.isExternal(p) 
  return 1; 
// If the node of position p is already external(a leaf) 
// -> return total num of external nodes as 1 

else
  total = 0;
  for (Position<E> c : children(p)) - for each child c in tree.children(p)
  total = total + countExternalNodes(T, c)
return total;


Q3: 
To count only the left side 
Method: public int countLeftExternalB(p);
p: a position p in a binary tree
Output: total num of left external nodes in a subtree rooted at p

// Let total be 0
total = 0;
// Checks if the left child exists, 
// -> if so, then check if it is an external

// If yes -> add 1 to total
// If no  -> total num of external left nodes atm + check for that node's children and loop
if left(p) != 0 {
  if isExternal(left(p)) {
    total += 1;
  }
  else {
    total = total + countLeftExternalB(left(p));
  }

// Check the right child (doesn't add 1 so it checks its children)
  if right(p) != null {
    total = total + countLeftExternal(right(p));
  }
 return total;
}

Q5: 
To count the total num of descendants of a particular node in the 
binary tree..
Method: public int countDescendantsB(p)
p: specific position of a node in a tree
Output: returns total num of descendants of node p

int total = 0;

if left(p) != null
  total = total + 1 + countDescendantsB(left(p));

if right(p) != null
  total = total  + 1 + countDescendantsB(right(p));

return total;


Tree || 
Q5: 
To find the diameter of a binary tree: 
Method: public int findDiamter(p)
p     : Position of (sub)tree
Output: returns diamater of that tree

Initialize diameter = 0;
if root == null -> return 0; (If the root of the tree is nothing -> no depth/diamter)
int leftHeight  -> if left(p) != null -> height_recursive(left(p)), otherwise return 0
int rightHeight -> if right(p) != null -> height_recursive(right(p)), otherwise return 0

int leftDiameter  -> if left(p) != null -> diameter(left(p)), otherwise return 0
int rightDiameter -> if right(p) != null -> diameter(right(p)), otherwise return 0

Use Math.max(..) to get the largest diameter among the rest.