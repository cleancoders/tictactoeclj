(ns tictactoeclj.core)

(defn- row-win? [player board]
  (some
    #(apply = (cons player %))
    (partition 3 board)))

(defn- rotate [board]
  (apply interleave (partition 3 board)))

(defn- middle [board]
  (nth board 4))

(defn- diagonals? [player board]
  (or (and (= player (first board))
           (= player (middle board))
           (= player (last board)))
      (and (= player (nth board 2))
           (= player (middle board))
           (= player (nth board 6)))))

(defn win? [player board]
  (or
    (row-win? player board)
    (row-win? player (rotate board))
    (diagonals? player board)))

(defn possible-moves [board]
  (filter #(nil? (nth board %)) (range 9)))