package models.structures;

import java.util.ArrayList;
import java.util.Iterator;

public class Union {
   private Union head;
   ArrayList<Union> edges;
   private int value;
   private int breaths;
   public int x;
   public int y;

   public Union() {
      resetParameters();
   }

   public Union union(Union newHead) {
      if (head != null) {
         return head.union(newHead);
      } else {
         newHead = newHead.find();
         if (find() == newHead) {
            return find();
         } else {
            head = newHead;
            edges.add(this);
            head.addEdges(edges);
            edges = new ArrayList<>();
            return head;
         }
      }
   }

   public Union find() {
      if (head == null) {
         return this;
      } else {
         return head.find();
      }
   }

   public void resetAllChildes() {
      for(Union edge : find().edges) {
         if (edge.edges.size() == 0) {
            edge.resetParameters();
         } else {
            edge.resetAllChildes();
         }
      }
      resetParameters();
   }

   public ArrayList<Union> getSet() {
      if (head != null) {
         return find().getSet();
      } else {
         ArrayList<Union> out = new ArrayList<>(edges);
         out.add(this);
         return out;
      }
   }

   public void resetParameters() {
      head = null;
      this.value = 0;
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
      find().breaths = breaths;
   }
}
