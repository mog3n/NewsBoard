(ns news-boi.prod
  (:require
    [news-boi.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
