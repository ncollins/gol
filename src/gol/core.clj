(ns gol.core
  (:require [clojure.spec.alpha :as spec]
            [expound.alpha :as expound]))

(def empty-board [[0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]])

(spec/def ::coords (spec/cat :x (spec/int-in 0 10) :y (spec/int-in 0 10)))
(spec/def ::cell #{0 1})
(spec/def ::row (spec/coll-of ::cell :kind vector? :count 10))
(spec/def ::board (spec/coll-of ::row :count 10))

(spec/fdef on-board?
  :args (spec/cat :coords ::coords :board ::board)
  :ret boolean?)

(defn on-board?
  "Checks if the pair `x` and `y` are on the `board`."
  [x y board]
  (let [height (count board)
        width (count (first board))]
    (spec/valid? ::coords [x y])))

(defn neighbors
  "Return the valid coordinates of neighbors."
  [x y]
  (let [translations [[-1 -1] [0 -1] [1 1]
                      [-1 0] [1 0]     ; don't include the node itself at [0 0]
                      [-1 1] [0 1] [1 -1]]]
    (->> translations
         (map (fn [[x' y']] [(+ x x') (+ y y')]))
         (filter #(spec/valid? ::coords %)))))

(comment

  (set! spec/*explain-out* (expound/custom-printer {:show-valid-values? true
                                                    :print-specs? false
                                                    :theme :figwheel-theme}))


  ;; Any live cell with two or three live neighbors lives on to the next generation.

  ;; Any live cell with more than three live neighbors dies, as if by overpopulation.

  ;; Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
  )
