(ns tictactoeclj.core)

(defn win? [player board]
  (some
    #(apply = (cons player %))
    (partition 3 board))

  (interleave (partition 3 board))

  )

(defn- rotate [board]
  (apply interleave (partition 3 board)))

