package ru.skillbranch.HomeTask3.models

class Bender (var status: Status = Status.NORMAl, var question: Question = Question.NAME) {

    fun askQuestion():String = when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }
    fun listenAnswer(answer:String) : Pair<String,Triple<Int,Int,Int>>{
        return if(question.answer.contains(answer)){
            question = question.nextQuestion()
            "Отлично - это правильный ответ!\n${question.question}" to status.color
        }else{
            status = status.nextStatus()
            "Это неправильный ответ!\n${question.question}" to status.color
        }
    }

    enum class  Status(val color: Triple<Int,Int,Int>){
        NORMAl(Triple(255,255,255)),
        WARNING(Triple(255,120,10)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,255,0));

        fun nextStatus():Status {
            return if (this.ordinal< values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }
    enum class  Question(val question:String, val answer:List<String>){
        NAME("Как меня зовут?", listOf("бендрер","bender")){
            override fun nextQuestion(): Question {
            return PROFESSION
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик","bender")){
            override fun nextQuestion(): Bender.Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл","дерево","metal","iron","wood")){
            override fun nextQuestion(): Bender.Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Bender.Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Bender.Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Bender.Question = IDLE
        };

        abstract fun nextQuestion():Question

    }
}