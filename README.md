# GameLife
该为个人参赛项目，现在比赛已经结束，这边主要将设计ui和目前完成结果图实例放上，并打算借此demo和自己设计的一些Ui继续进行设计开发。
## 一、项目来源
疫情期间，最让我苦恼的事情就是每天要早起打卡，而最让我放松的事情就是每天偶尔打打游戏。可细看，游戏虽然魅力十足，但过于沉迷却会浪费时间；生活虽然意义非凡，但缺乏乐趣让人难以坚持。
当然并不是每个人都缺少对生活的乐趣，而是很多人在人生的规划上没有好好的走每一步。我相信人生肯定是多姿多彩的，至少在你付出后，所获得的一切都是值得的。<br>

于是，我们有了这样的想法，能否在自律的同时又能保持高度的乐趣？于是游戏人生这个项目的概念就在我们的讨论中出现了。把整个人生像游戏一样度过那会多么有意思？每天，通过软件，你能像游戏人物一样获取到任务，然后在生活里完成它。这些任务都是你近期想要完成的事项，比如每天一定数量的俯卧撑，实现某一技术的掌握，还有早睡早起等安排。<br>

软件会统计你每日对这些任务的完成情况，并赋予你经验值，游戏币，游戏中人物也会随着你每日的任务完成不断升级，变强，你还可以利用游戏币给在虚拟商城进行购物，给游戏人物进行装扮。<br>

那么在某一刻，游戏的次元壁或许被打破了，游戏人物的人生随着你的人生的丰盈而改变，如果说能把人生变成像游戏一样有趣，那应该就是我们的创意了。
## 二、项目设计
#### 项目概况
游戏人生 —— 一款将自律和游戏相结合的软件。“用户”接受app上系统派发的任务挑战，在现实世界进行锻炼、学习等不同方面的自我提升。若“用户”完成对应挑战，游戏中的角色也会获得相应的经验，属性，并随着经验，和对应属性值增加，会以类似养成的方式进行成长，使用户在获得游戏乐趣的同时，逐渐养成自律的习惯并完成真实世界的人生目标。
#### 项目设计理念
每个人都期望变得更好，“游戏人生”将生活与游戏相结合，帮助想要提升自己但无从下手、难以坚持的人们开始真实的改变，让更多的人，更轻松的保持自律、实现目标，走向美好未来。
应用采取成长游戏为吸引点的理念，是因为这种不会占用大量时间的游戏方式更适合将用户的注意力放在任务和自己本身，并且将这种成果以可见化的方式展现出来会让用户对自己的努力有一定认可。而认可正是不断坚持下来的主要动力。
#### 主要思路
主要思路是，新用户初次进入app的时候系统会进行初步数据的选择来采集用户自身情况，然后让用户选择想达到的目标，例：想要达成的目标类型是学习，习惯还是锻炼。根据这些数据，我们的应用在推送任务的时候会有更好的针对性，然后系统会在一开始生成相应的虚拟人物形象。而用户通过完成App中每天派发的任务，让虚拟用户获得属性点，经验以及金币等，让虚拟角色随着现实生活中自己的提升而提升。打个简单的比方，用户自己每天锻炼，那么自己的虚拟人物也变得越来越强壮。<br>
![Image](http://kan.027cgb.com/630788/%E6%B8%B8%E6%88%8F%E6%80%9D%E8%B7%AF.jpg)
## 三、项目设计UI
#### 1.任务子系统<br>
##### (1)为了让系统生成的任务更符合用户的需求，所以会在最开始让用户进行需求的填写，根据用户选择的目标倾向派发相应任务挑战，有学习型，习惯型和拓展型等。当然，这些需求在后面也是可以修改的，以满足不同时期的需求。
##### (2)派发的任务难度可以自己选择，在一定期限内任务项目不变，任务参数随任务完成情况改变。同时，每天可做的任务数量有上限，每周任务天数有上下限，例：一周做任务天数需达2天不超过6天，当天需完成1个任务但不得超过10个任务等限制，若“用户”选择做超额任务，则超过任务次数之外所作的任务奖励减半，避免短时间内任务量过重，以防止游戏性重于生活性，不然就与我们的初衷有违了
##### (3）此外，因为系统任务有着不可避免的局限性，为了满足不同的任务需求，用户还可以自定义任务，对目标倾向、任务内容计时方式、时间等进行设置(同时系统根据这些计算奖励值)。<br>
 ![Image](http://kan.027cgb.com/630788/%E9%80%89%E6%8B%A9.jpg)
 ![Image](http://kan.027cgb.com/630788/%E6%B7%BB%E5%8A%A0%E4%BB%BB%E5%8A%A1.jpg)
 ![Image](http://kan.027cgb.com/630788/S00621-174116.jpg)
#### 2. 角色子系统<br>
##### (1)角色属性分为德、智、体、美、劳。和生活相似的是，不同的属性获取的方式不同，因为生活本就是多姿多彩，单一的生活方式不是我们推崇的，所以我们根据习惯，学习，锻炼和人物形象，还有坚持时间分别进行了赋值计算。
##### (2)除了属性，通过做任务还能获得金币和经验，金币可以用来解锁装扮物件、宠物。而经验可以进行升级，人物也会开始成长，且等级达到一定程度，人物就会开始变化。其中根据人物各项属性、目前等级、获取成就等各方面进行计算得出综合分数，并依据一些已有数据进行展示。
##### (4)衣帽间设计，为了体现养成游戏的乐趣，开发衣帽间，进行角色装扮是必不可少的，其中装扮物品的来源包括成就奖励，升级奖励和金币购买，通过多维的方式获取，就会让用户对任务产生需求和以来关系。其中不同人物成长时期的衣帽间是不同的，具体参考人物成长说明图。
 ![Image](http://kan.027cgb.com/630788/%E6%88%91%E7%9A%84%E8%B5%84%E6%96%99_%E8%8F%9C%E5%8D%95%E6%A0%8F.jpg)
 ![Image](http://kan.027cgb.com/630788/%E8%A1%A3%E5%B8%BD%E9%97%B4.jpg)
#### 3. 统计子系统<br>
(1)以每个月为大纲统计，显示每月三大类型在自己完成任务时间的占比和每天的均和统计包括平均时间和最长时间，让用户对自己使用有一定了解，更加详细的相关推荐则需点击更多，如下右图。
(2)为了更好展示目前进度，还设有成就奖励机制和排行榜机制，其中成就是为了展现游戏性，让用户通过这些成就了解自己坚持的方向和程度，同时获取成就奖励增加趣味。而排行榜的设立，让竞争性融入，目前，一个简单的游戏机制就已经构成。<br>
 ![Image text](http://kan.027cgb.com/630788/%E4%BA%BA%E7%94%9F%E8%BF%9B%E5%B1%95_%E8%8F%9C%E5%8D%95%E6%A0%8F.jpg)
 ![Image text](http://kan.027cgb.com/630788/%E6%95%B0%E6%8D%AE%E7%BB%9F%E8%AE%A11_%E8%8F%9C%E5%8D%95%E6%A0%8F.jpg)
#### 4. 动态分享子系统<br>
(1)用户可以在设计界面分享自己培养的虚拟角色、角色排名等，当进行一些分享的时候，能使用户获得独特的成就感与体验，产生追求价值，从而不断挑战新的任务。
(2)因为沟通思考是人类特有的属性，通过社交沟通用户可以分享自己的看法和感受，互相的了解和鼓励就会使一个人的改变之路不再孤独，游戏人生的特性也在这再一次表现出来。因为对于用户来说，这不在是把里面的任务拿出来做完这么简单了，因为分享，让你把自己的生活融了进去。
 ![Image](http://kan.027cgb.com/630788/%E7%A4%BE%E4%BA%A4_%E8%8F%9C%E5%8D%95%E6%A0%8F.jpg)
 ![Image](http://kan.027cgb.com/630788/%E9%97%B9%E9%92%9F.gif)
