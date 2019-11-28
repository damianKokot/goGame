package models.structures;

import java.util.ArrayList;

public class Union {
   private Union head;
   private ArrayList<Union> edges;
   private int value;
   private int breaths;

   public Union() {
      resetParameters();
   }


   public Union union(Union newHead) {
      if(head != null) {
         return head.union(newHead);
      }
      newHead = newHead.find();
      if(find() == newHead) {
         return find();
      }
      newHead.breaths += find().breaths;
      head = newHead;
      edges.add(this);
      head.addEdges(edges);

      edges = new ArrayList<>();
      return head;

   }
   public Union find() {
      if(head == null) {
         return this;
      } else {
         return head.find();
      }
   }

   public void resetAllChildes() {
      for (Union edge : find().edges) {
         if(edge.edges.size() == 0) {
            edge.resetParameters();
         } else {
            edge.resetAllChildes();
         }
      }
      resetParameters();
   }

   private void resetParameters() {
      head = null;
      value = 0;
      edges = new ArrayList<>();
   }

   private void addEdges(ArrayList<Union> edges) {
      this.edges.addAll(edges);
   }

   public int getValue() {
      return find().value;
   }

   public void setValue(int value) {
      find().value = value;
   }

   public int getBreaths() {
      return find().breaths;
   }

   public void setBreaths(int breaths) {
      this.breaths = breaths;
   }

   public void substrateBreath() {
      find().breaths--;
   }
}
