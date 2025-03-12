public class StackLinkedList {
        Node head;
        Node tail;
        int size;

        public StackLinkedList() {
            head = null;
            tail = null;
            size = 0;
        }

        public StackLinkedList(Node head) {
            this.head = head;
            this.tail = head;
            this.size = 1;
        }

        public void push(Node n) {
            if(this.size == 5){
                System.out.println("stack overflow");
            }
            else{
                this.tail.next = n;
                this.tail = n;
                this.size++;
            }
        }

        public Node pop() {
            if (this.tail == null) {
                System.out.println("Stack is empty");
                return null;
            }
            else{
                return this.tail;
            }
        }
}