(ns tictactoeclj.core-spec
  (:require [clojure.string :as str]
            [speclj.core :refer :all]
            [tictactoeclj.core :refer :all]))

(defn- char->mark [char]
  (case char
    \x :x
    \o :o
    nil))

(defn- ->board [board-str]
  (map char->mark
       (filter #(not= \| %) board-str)))

(defn- row-> [row]
  (apply str
         (map #(case % :x \x :o \o \.) row)))

(defn board-> [board]
  (->> (partition 3 board)
       (map row->)
       (str/join "|")))

(describe "TTT"
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
      (should-not (win? :x (->board "..x|..x|.x."))))

    (it "win for x diagonals"
      (should (win? :x (->board "x..|.x.|..x")))
      (should (win? :x (->board "..x|.x.|x..")))
      (should-not (win? :x (->board "o..|.o.|..o"))))

    (it "gives me possible moves"
      (should= [0 1 2 3 4 5 6 7 8]
               (possible-moves (->board "...|...|...")))
      (should= [0 1 2 3]
               (possible-moves
                 (->board "...|.xo|xox")))
      (should= []
               (possible-moves
                 (->board "oox|oxo|xox"))))

    (it "records moves"
      (should= "x..|...|..."
               (board->
                 (move :x 0 (->board "...|...|..."))))))

  )