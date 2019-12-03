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
      this.resetParameters();
   }

   public Union union(Union newHead) {
      if (this.head != null) {
         return this.head.union(newHead);
      } else {
         newHead = newHead.find();
         if (this.find() == newHead) {
            return this.find();
         } else {
            this.head = newHead;
            this.edges.add(this);
            this.head.addEdges(this.edges);
            this.edges = new ArrayList<>();
            return this.head;
         }
      }
   }

   public Union find() {
      if(this.head == null) {
         return this;
      } else {
         return head.find();
      }
   }

   public void resetAllChildes() {
      for(Union edge : this.find().edges) {
         if (edge.edges.size() == 0) {
            edge.resetParameters();
         } else {
            edge.resetAllChildes();
         }
      }

      this.resetParameters();
   }

   public ArrayList<Union> getSet() {
      if (this.head != null) {
         return this.find().getSet();
      } else {
         ArrayList<Union> out = new ArrayList<>(this.edges);
         out.add(this);
         return out;
      }
   }

   public void resetParameters() {
      this.head = null;
      this.value = 0;
      this.edges = new ArrayList<>();
   }

   private void addEdges(ArrayList<Union> edges) {
      this.edges.addAll(edges);
   }

   public int getValue() {
      return this.find().value;
   }

   public void setValue(int value) {
      this.find().value = value;
   }

   public int getBreaths() {
      return this.find().breaths;
   }

   public void setBreaths(int breaths) {
      this.find().breaths = breaths;
   }

   public void substrateBreath() {
      --this.find().breaths;
   }
}
