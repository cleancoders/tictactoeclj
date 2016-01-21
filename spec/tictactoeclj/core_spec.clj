(ns tictactoeclj.core-spec
  (:require [speclj.core :refer :all]
            [tictactoeclj.core :refer :all]))

(defn- char->mark [char]
  (case char
    \x :x
    \o :o
    nil))

(defn- ->board [board-str]
  (map char->mark
       (filter #(not= \| %) board-str)))

(describe "a test"
  (context "board"
    (it "empty board is not a win for x"
      (should-not (win? :x (->board "...|...|...")))
      (should-not (win? :o (->board "...|...|..."))))

    (it "win for x rows"
      (should (win? :x (->board "xxx|...|...")))
      (should (win? :x (->board "...|xxx|...")))
      (should (win? :x (->board "...|...|xxx")))
      (should-not (win? :x (->board "...|ooo|...")))
      (should-not (win? :x (->board "..x|x..|..x"))))

    (it "win for x columns"
      (should (win? :x (->board "x..|x..|x..")))
      (should (win? :x (->board ".x.|.x.|.x.")))
      (should (win? :x (->board "..x|..x|..x")))
      (should-not (win? :x (->board "..o|..o|..o")))
      (should-not (win? :x (->board "..x|..x|.x.")))

      )
    )


  )