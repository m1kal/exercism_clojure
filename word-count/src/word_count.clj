(ns word-count
  (:use [clojure.string :only [split lower-case]])
)

(defn word-count [words]
  (frequencies (split (lower-case words) #"[^a-z0-9]+"))
)
