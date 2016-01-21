(ns tictactoeclj.core-spec
  (:require [speclj.core :refer :all]
            [tictactoeclj.core :refer :all]))

(defn ->board [board-str]
  (take 9 (repeat nil))
  )

(describe "a test"
  (context "board"
    (it "empty board is not a win for x"
      (should= false (win? :x (->board "...|...|...")))
      (should= false (win? :o (->board "...|...|..."))))

    (it "win for x rows"
      (should= true (win? :x (->board "xxx|...|...")))
      (should= true (win? :x (->board "xxx|...|...")))
      (should= true (win? :x (->board "xxx|...|..."))))
    )
  )