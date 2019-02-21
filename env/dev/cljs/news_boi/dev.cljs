(ns ^:figwheel-no-load news-boi.dev
  (:require
    [news-boi.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
