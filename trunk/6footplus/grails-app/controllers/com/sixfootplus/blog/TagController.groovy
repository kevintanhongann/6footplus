package com.sixfootplus.blog

import org.grails.taggable.*

class TagController {

    def show = {

        //maximum tags to return
        def maxTags = (params?.maxTags ?: 10).toInteger()

        //mapping tag name with count e.g. [techie:16]
        def tagCount = [:]

        TagLink.withCriteria() {
            createAlias("tag", "tag")
            projections {
                groupProperty("tag.name")
                count("tag.id")
            }
            cache true
        }.each { row -> //map the collection to a map
            tagCount[row[0]] = row[1]
        }

        //sort by highest count
        tagCount = tagCount.sort {a, b -> b.value <=> a.value}

        //determine index of range end
        def range = (tagCount.size() > maxTags) ? maxTags - 1 : tagCount.size() -1

        //limit only if there's at least one tag
        if(range > -1){
            def subList = (tagCount.keySet() as List)[0..range]
            tagCount = tagCount.subMap(subList)
        }

        [tags: tagCount]
    }

    def cloud = {

        params.maxTags = 400
        show()
    }
}
