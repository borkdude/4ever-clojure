(ns app.core
  (:require [app.routes :as routes]
            [reagent.dom :as rdom]
            [reitit.frontend.easy :as rfe]))

(def notification
  [:div {:style {:background-color "#D6D0FD"
                 :position "absolute"
                 :top 0
                 :left 0
                 :width "100%"
                 :text-align "center"}}
   [:div {:style {:padding "0.5rem"}}
    [:small
     "Solutions archive browsable from each problem page 🎉 Huge thanks to Alan!!"]]])

(defn header []
  [:header
   [:h1 "4ever-clojure"]
   [:p
    [:small
     [:a {:href (rfe/href :home)
          :data-reitit-handle-click false} "home"]
     " | "
     [:a {:href "https://github.com/oxalorg/4ever-clojure"} "github"]
     " | "
     "Built with ❤ by "
     [:a {:href "https://twitter.com/oxalorg"} "@oxalorg"]
     " and "
     [:a {:href "https://twitter.com/borkdude"} "@borkdude"]]]
   notification])

(defn main []
  [:div
   [header]
   (when-let [match @routes/match]
     (let [view (:view (:data match))]
       [view match]))])

(defn ^:dev/after-load mount []
  (rdom/render
   [main]
   (js/document.getElementById "app")))

(defn init! []
  (routes/init!)
  (mount))

(init!)
